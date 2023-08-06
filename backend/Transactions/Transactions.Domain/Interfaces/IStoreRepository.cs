using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;

namespace Transactions.Services.Interfaces
{
    public interface IStoreRepository : IBaseRepository<StoreModel>
    {
        Task<List<object>> GetStores();

        Task CreateStore();
    }
}
