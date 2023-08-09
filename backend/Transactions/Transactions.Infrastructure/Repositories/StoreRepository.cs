using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;

namespace Transactions.Infrastructure.Repositories
{
    public class StoreRepository : BaseRepository<StoreModel>, IStoreRepository
    {
        protected readonly ApplicationDbContext _context;

        public StoreRepository(ApplicationDbContext context) : base(context)
        {
            _context = context;
        }

        public async Task<StoreModel> GetStoreByName(string name)
        {
            return await _context.Store.FirstOrDefaultAsync(x => x.Name == name);
        }
    }
}
