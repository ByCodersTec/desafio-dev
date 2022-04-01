using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.Core.Repositories
{
    public interface ITransactionRepository
    {
        Task InsertAsync(Transactions transactions);
    }
}