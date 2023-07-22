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
            var result = new StringBuilder();

            var columns = _docDefinitionRepository.GetAll(x => x.Code == "CNAB", null, "Columns", null)?.FirstOrDefault()?.Columns;
            if (columns?.Any() == true)
            {
                using (var reader = new StreamReader(request.file))
                {
                    while (reader.Peek() >= 0)
                        result.AppendLine(await reader.ReadLineAsync());
                }
                var fileLines = result.ToString();
                var linesToProcess = _docParserService.ParseFileLinseFromString<List<CnabImportViewModel>>(
                    fileLines,
                    columns.Select(c => new DocColumnViewModel { }).ToList(),
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
