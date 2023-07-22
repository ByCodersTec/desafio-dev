using ByCodersTec.StoreDataImporter.DocParserService;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Repository.EF;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Mapping;
using ByCodersTec.StoreDataImporter.Services.Message;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Implementation
{
    public class TransactionService : ITransactionService
    {
        readonly ITransactionRepository _transactionRepository;
        readonly ITransactionTypeRepository _transactionTypeRepository;
        readonly IStoreRepository _storeRepository; 
        readonly IDocDefinitionRepository _docDefinitionRepository;
        readonly IUnitOfWork _unitOfWork;
        readonly ByCodersTec.StoreDataImporter.Domain.IMessageService _messageService;
        readonly IDocParserService _docParserService;
        public TransactionService(
            IUserRepository userRepository,
            IUnitOfWork unitOfWork,
            ByCodersTec.StoreDataImporter.Domain.IMessageService messageService,
            IDocParserService docParserService,
            ITransactionRepository transactionRepository,
            IDocDefinitionRepository docDefinitionRepository,
            IStoreRepository storeRepository,
            ITransactionTypeRepository transactionTypeRepository
        )
        {
            _docDefinitionRepository = docDefinitionRepository;
            _transactionRepository = transactionRepository;
            _unitOfWork = unitOfWork;
            _messageService = messageService;
            _docParserService = docParserService;
            _storeRepository = storeRepository;
            _transactionTypeRepository = transactionTypeRepository;
        }

        public async Task<AddTransactionsFromFileResponse> AddTransactionsCNABFromFile(AddTransactionsFromFileRequest request)
        {
            var response = new AddTransactionsFromFileResponse();
            var fileLines = new List<string>();

            var columns = _docDefinitionRepository.GetAll(x => x.Code == "CNAB", null, "Columns", null)?.FirstOrDefault()?.Columns;
            if (columns?.Any() == true)
            {
                using (var reader = new StreamReader(request.file))
                {
                    while (reader.Peek() >= 0)
                        fileLines.Add(await reader.ReadLineAsync());
                }

                var linesToProcess = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(
                    fileLines,
                    columns.Select(c => new DocColumnViewModel {
                        ClassPropName = c.ClassPropName,
                        Description = c.Description,
                        End = c.End,
                        Lenght = c.Lenght,
                        Name = c.Name,
                        Start = c.Start,
                        Type = (DocDefinitionColumnTypeEnumViewModel)c.Type
                    }).ToList(),
                    zeroBased: true
                );

                foreach (var item in linesToProcess)
                {
                    _messageService.Enqueue(item);
                }
            }
            return response;
        }

        public AddTransactionResponse AddTransaction(AddTransactionRequest request)
        {
            var response = new AddTransactionResponse();

            var formatedDate = Regex.Replace(request.model.Date + request.model.Time, @"(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})", "$1-$2-$3 $4:$5:$6");
            var date = DateTime.Parse(formatedDate);

            var transactionType = _transactionTypeRepository.GetAll(t => t.Code == request.model.Type).FirstOrDefault();
            var store = _storeRepository.GetAll(s => s.Name == request.model.StoreName)?.FirstOrDefault();
            if (store == null)
            {
                store = new Store {
                    LegalPerson = request.model.Dealer,
                    Name = request.model.StoreName
                };
                _storeRepository.Insert(store);
            }

            var transaction = new Transaction
            {
                Card = request.model.Card,
                Date = date,
                Document = request.model.Document,
                Identifier = request.model.LineHash,
                Type = transactionType,
                Store = store,
                Value = decimal.Parse(request.model.Val) / 100
            };

            if (!_transactionRepository.Exists(t => t.Identifier == transaction.Identifier))
                _transactionRepository.Insert(transaction);

            return response;
        }

        public GetTransactionsResponse GetTransactions(GetTransactionsRequest request)
        {
            var response = new GetTransactionsResponse();
            var transactions = _transactionRepository.GetAll(filter: null, orderBy: null, includeProperties: "Store,Type", includeSecondProperties: null);
            response.transaction = transactions.Select(t => new TransactionViewModel {
                Card = t.Card,
                Date=t.Date,
                Document=t.Document,
                Identifier=t.Identifier,
                StoreName=t.Store.Name,
                Type=t.Type.Code,
                Value=t.Value
            }).ToList();
            return response;
        }
    }
}
