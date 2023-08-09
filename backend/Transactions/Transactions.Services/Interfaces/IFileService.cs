using Microsoft.AspNetCore.Http;

namespace Transactions.Services.Interfaces
{
    public interface IFileService
    {
        Task ProcessFile(IFormFile file);
    }
}
