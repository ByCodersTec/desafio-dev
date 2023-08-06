namespace Transactions.Services.Interfaces
{
    public interface IAccountService
    {
        Task<List<object>> GetStores();
        Task<List<object>> GetOperationsByStore(string storeId);
    }
}
