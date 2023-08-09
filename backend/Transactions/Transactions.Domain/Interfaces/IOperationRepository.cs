using Transactions.Domain.Models;

namespace Transactions.Domain.Interfaces
{
    public interface IOperationRepository : IBaseRepository<OperationModel>
    {
        Task<List<OperationModel>> GetOperationsByStore(Guid storeId);
    }
}
