using Transactions.Domain.Models;

namespace Transactions.Domain.Interfaces
{
    public interface IStoreRepository : IBaseRepository<StoreModel>
    {
        Task<StoreModel> GetStoreByName(string name);
    }
}
