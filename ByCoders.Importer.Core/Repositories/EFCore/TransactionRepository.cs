using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.Core.Repositories.EFCore
{
    public class TransactionRepository : ITransactionRepository
    {
        private readonly AppDbContext dbContext;

        public TransactionRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }


        public async Task InsertAsync(Transactions transactions)
        {
            await dbContext.Set<Transactions>().AddAsync(transactions);
        }
    }
}