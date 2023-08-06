using Microsoft.AspNetCore.Mvc;
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
        public async Task<string> GetStores()
        {
            await Task.Delay(300);

            return "GetStores Works";
        }

        [HttpGet("stores/{storeId}/operations")]
        public async Task<string> GetOperationsByStore(string storeId)
        {
            await Task.Delay(300);

            return "GetOperationsByStore Works";
        }
    }
}