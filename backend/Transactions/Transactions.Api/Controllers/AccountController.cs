using Microsoft.AspNetCore.Mvc;
using Transactions.Application.Dtos;
using Transactions.Application.ViewModels;
using Transactions.Services.Interfaces;

namespace Transactions.Api.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AccountController : ControllerBase
    {
        protected readonly IAccountService _accountService;

        public AccountController(IAccountService accountService)
        {
            _accountService = accountService;
        }

        [HttpGet("stores")]
        public async Task<List<StoreViewModel>> GetStores()
        {
            return await _accountService.GetStores();
        }

        [HttpGet("stores/{storeId}/operations")]
        public async Task<OperationsDetailsDto> GetOperationsByStore(string storeId)
        {
            return await _accountService.GetOperationsByStore(Guid.Parse(storeId));
        }
    }
}