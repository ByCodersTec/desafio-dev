namespace Transactions.Services.Interfaces
{
    public interface IStoreRepository
    {
        Task<List<object>> GetStores();

        Task CreateStore();
    }
}
