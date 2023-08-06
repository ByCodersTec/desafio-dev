using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;

namespace Transactions.Services.Interfaces
{
    public interface IStoreRepository : IBaseRepository<StoreModel>
    {
        Task<StoreModel> GetStoreByName(string name);
    }
}
