using Transactions.Services.Interfaces;

namespace Transactions.Services.Services
{
    public class AccountService : IAccountService
    {
        public AccountService()
        {

        }

        public async Task<List<object>> GetStores()
        {
            await Task.Delay(300);

            return new List<object>();
        }

        public async Task<List<object>> GetOperationsByStore(string storeId)
        {
            await Task.Delay(300);

            return new List<object>();
        }
    }
}
