using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.Core.Repositories
{
    public interface ITransactionTypeRepository
    {
        IQueryable<TransactionTypes> List();
    }
}