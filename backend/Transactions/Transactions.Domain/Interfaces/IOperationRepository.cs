namespace Transactions.Services.Interfaces
{
    public interface IOperationRepository
    {
        Task<List<object>> GetOperationsByStore(string storeId);

        Task CreateOperation();
    }
}
