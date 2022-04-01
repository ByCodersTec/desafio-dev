using ByCoders.Importer.Core;
using ByCoders.Importer.Core.BusinessEntities;
using ByCoders.Importer.Core.Repositories;

namespace ByCoders.Importer.WebApi.Services.Handlers
{
    public class TransactionsServiceDefault : ITransactionService
    {
        private readonly AppDbContext dbContext;
        private readonly ITransactionRepository transactionRepository;

        public TransactionsServiceDefault(AppDbContext dbContext, ITransactionRepository transactionRepository)
        {
            this.dbContext = dbContext;
            this.transactionRepository = transactionRepository;
        }

        public async Task ImportTransactionsFromFile(IFormFile file)
        {
            using var fileStream = file.OpenReadStream();
            using var stream = new StreamReader(fileStream);

            using var dbTransaction = await dbContext.Database.BeginTransactionAsync();
            try
            {
                string line;
                while ((line = stream.ReadLine() ?? string.Empty) != string.Empty)
                {
                    var recordTransaction = new Transactions();

                    recordTransaction.TransactionTypeID = Convert.ToInt32(line[..1]);
                    recordTransaction.Date = new DateTime(
                        Convert.ToInt32(line.Substring(1, 4)),
                        Convert.ToInt32(line.Substring(5, 2)),
                        Convert.ToInt32(line.Substring(7, 2)),
                        Convert.ToInt32(line.Substring(42, 2)),
                        Convert.ToInt32(line.Substring(44, 2)),
                        Convert.ToInt32(line.Substring(46, 2))
                    );

                    recordTransaction.Value = Convert.ToDecimal(line.Substring(9, 10)) / 100;

                    recordTransaction.CPF = line.Substring(19, 11);
                    recordTransaction.CardNumber = line.Substring(30, 12);
                    recordTransaction.EmporiumOwner = line.Substring(48, 14).Trim();
                    recordTransaction.EmporiumName = line.Substring(62, 18).Trim();

                    await transactionRepository.InsertAsync(recordTransaction);
                }

                dbContext.SaveChanges();
                await dbTransaction.CommitAsync();
            }
            catch
            {
                await dbTransaction.RollbackAsync();
            }
        }
    }
}