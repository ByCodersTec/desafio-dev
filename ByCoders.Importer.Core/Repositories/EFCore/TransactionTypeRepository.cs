using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.Core.Repositories.EFCore
{
    public class TransactionTypeRepository : ITransactionTypeRepository
    {
        private readonly AppDbContext dbContext;

        public TransactionTypeRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }


        public IQueryable<TransactionTypes> List()
        {
            return dbContext.Set<TransactionTypes>().AsQueryable();
        }
    }
}