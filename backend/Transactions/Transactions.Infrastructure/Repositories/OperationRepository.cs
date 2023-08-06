using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Services.Interfaces;

namespace Transactions.Infrastructure.Repositories
{
    public class OperationRepository : BaseRepository<OperationModel>, IOperationRepository
    {
        protected readonly ApplicationDbContext _context;

        public OperationRepository(ApplicationDbContext context) : base(context)
        {
            _context = context;
        }

        public async Task<List<object>> GetOperationsByStore(string storeId)
        {
            await Task.Delay(300);

            return new List<object>();
        }

        public async Task CreateOperation()
        {
            await Task.Delay(300);
        }
    }
}