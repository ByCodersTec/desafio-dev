using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Services.Interfaces;

namespace Transactions.Infrastructure.Repositories
{
    public class StoreRepository : BaseRepository<StoreModel>, IStoreRepository
    {
        protected readonly ApplicationDbContext _context;

        public StoreRepository(ApplicationDbContext context) : base(context)
        {
            _context = context;
        }

        public async Task<List<object>> GetStores()
        {
            await Task.Delay(300);

            return new List<object>();
        }

        public async Task CreateStore()
        {
            await Task.Delay(300);
        }
    }
}
