using Transactions.Services.Interfaces;

namespace Transactions.Infrastructure.Repositories
{
    public class OperationRepository : IOperationRepository
    {
        public OperationRepository()
        {

        }

        public async Task<List<object>> GetOperationsByStore(string storeId)
        {
            await Task.Delay(300);

            return new List<object>();
        }

        public async Task CreateOperation()
        {
            await Task.Delay(300);
        }
    }
}