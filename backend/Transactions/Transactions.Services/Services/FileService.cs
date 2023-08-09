using Microsoft.AspNetCore.Http;
using Transactions.Application.Dtos;
using Transactions.Domain.Interfaces;
using Transactions.Services.Interfaces;

namespace Transactions.Services.Services
{
    public class FileService : IFileService
    {
        protected readonly IAccountService _accountService;
        protected readonly IOperationRepository _operationRepository;

        public FileService(IAccountService accountService)
        {
            _accountService = accountService;
        }

        public async Task ProcessFile(IFormFile file)
        {
            var operations = new List<FileFormatDto>();

            using (var reader = new StreamReader(file.OpenReadStream()))
            {
                while (reader.Peek() >= 0)
                {
                    var row = reader.ReadLine();

                    var operation = new FileFormatDto()
                    {
                        TransactionType = int.Parse(row.Substring(0, 1)),
                        Date = Convert.ToUInt64(row.Substring(1, 8)).ToString(@"0000-00-00"),
                        Value = int.Parse(row.Substring(9, 10)),
                        Document = Convert.ToUInt64(row.Substring(19, 11)).ToString(@"000\.000\.000\-00"),
                        Card = row.Substring(30, 12),
                        Time = Convert.ToUInt64(row.Substring(42, 6)).ToString(@"00:00:00"),
                        StoreOwnerName = row.Substring(48, 14).Trim(),
                        StoreName = row.Substring(62).Trim()
                    };

                    operations.Add(operation);
                }
            }

            await _accountService.ProcessOperations(operations);
        }
    }
}
