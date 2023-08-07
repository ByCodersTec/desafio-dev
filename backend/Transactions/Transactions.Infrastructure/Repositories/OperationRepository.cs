using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;

namespace Transactions.Infrastructure.Repositories
{
    public class OperationRepository : BaseRepository<OperationModel>, IOperationRepository
    {
        protected readonly ApplicationDbContext _context;

        public OperationRepository(ApplicationDbContext context) : base(context)
        {
            _context = context;
        }

        public async Task<List<OperationModel>> GetOperationsByStore(Guid storeId)
        {
            return await _context.Operation.Where(x => x.StoreId == storeId).ToListAsync();
        }
    }
}