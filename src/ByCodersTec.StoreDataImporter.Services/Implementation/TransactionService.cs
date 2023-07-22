using ByCodersTec.StoreDataImporter.DocParserService;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Repository.EF;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Mapping;
using ByCodersTec.StoreDataImporter.Services.Message;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Implementation
{
    public class TransactionService
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

        
    }
}
