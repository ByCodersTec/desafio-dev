using Transactions.Services.Interfaces;

namespace Transactions.Services.Services
{
    public class FileService : IFileService
    {
        public FileService()
        {

        }

        public async Task ProcessFile()
        {
            await Task.Delay(300);
        }
    }
}
