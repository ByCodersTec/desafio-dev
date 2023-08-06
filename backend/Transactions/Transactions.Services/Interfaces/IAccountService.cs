using Transactions.Application.Dtos;
using Transactions.Application.ViewModels;

namespace Transactions.Services.Interfaces
{
    public interface IAccountService
    {
        Task<List<StoreViewModel>> GetStores();
        Task<List<OperationViewModel>> GetOperationsByStore(Guid storeId);
        Task ProcessOperations(List<FileFormatDto> operations);
    }
}
