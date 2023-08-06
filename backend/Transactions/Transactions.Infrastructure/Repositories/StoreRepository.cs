using Transactions.Services.Interfaces;

namespace Transactions.Infrastructure.Repositories
{
    public class StoreRepository : IStoreRepository
    {
        public StoreRepository()
        {

        }

        public async Task<List<object>> GetStores()
        {
            await Task.Delay(300);

            return new List<object>();
        }

        public async Task CreateStore()
        {
            await Task.Delay(300);
        }
    }
}
