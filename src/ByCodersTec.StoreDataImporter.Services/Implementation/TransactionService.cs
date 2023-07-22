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
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Implementation
{
    public class TransactionService : ITransactionService
    {
        readonly ITransactionRepository _transactionRepository;
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
            IDocDefinitionRepository docDefinitionRepository
        )
        {
            _docDefinitionRepository = docDefinitionRepository;
            _transactionRepository = transactionRepository;
            _unitOfWork = unitOfWork;
            _messageService = messageService;
            _docParserService = docParserService;
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
    }
}
