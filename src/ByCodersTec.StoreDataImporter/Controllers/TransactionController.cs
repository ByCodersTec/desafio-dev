using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.ViewModel;
using Microsoft.AspNetCore.Mvc;
using System.Text;

namespace ByCodersTec.StoreDataImporter.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class TransactionController : ControllerBase
    {
        private readonly ILogger<TransactionController> _logger;
        readonly ITransactionService _transactionService;
        readonly IMessageService _messageService;
        public TransactionController(
            ILogger<TransactionController> logger,
            IUserService userService,
            IMessageService messageService,
            ITransactionService transactionService
        )
        {
            _logger = logger;
            _messageService = messageService;
            _transactionService = transactionService;
        }

        [HttpPost("enqueue")]
        public async Task<ApiResponse<bool>> EnqueueUser(IFormFile file)
        {
            await _transactionService.AddTransactionsCNABFromFile(new Services.Message.AddTransactionsFromFileRequest { file = file.OpenReadStream() });
            return ApiResponse<bool>.CreateResponse(true);
        }

        [HttpGet("")]
        public ApiResponse<List<TransactionViewModel>> GetAll()
        {
            var response = _transactionService.GetTransactions(new Services.Message.GetTransactionsRequest { }).transaction;
            return ApiResponse<List<TransactionViewModel>>.CreateResponse(response);
        }
    }
}