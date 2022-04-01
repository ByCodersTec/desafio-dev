using ByCoders.Importer.Core;
using ByCoders.Importer.Core.BusinessEntities;
using ByCoders.Importer.Core.Repositories;
using ByCoders.Importer.WebApi.Exceptions;
using System.Linq;

namespace ByCoders.Importer.WebApi.Services.Handlers
{
    public class TransactionsServiceDefault : ITransactionService
    {
        private readonly AppDbContext dbContext;
        private readonly ITransactionTypeRepository transactionTypeRepository;
        private readonly ITransactionRepository transactionRepository;

        public TransactionsServiceDefault(AppDbContext dbContext, ITransactionTypeRepository transactionTypeRepository, ITransactionRepository transactionRepository)
        {
            this.dbContext = dbContext;
            this.transactionTypeRepository = transactionTypeRepository;
            this.transactionRepository = transactionRepository;
        }

        public async Task ImportTransactionsFromFile(IFormFile file)
        {
            using var fileStream = file.OpenReadStream();
            using var stream = new StreamReader(fileStream);

            var transactionTypesIds = transactionTypeRepository.List().Select(x => x.ID).ToArray();

            using var dbTransaction = await dbContext.Database.BeginTransactionAsync();
            try
            {
                string line;
                while ((line = stream.ReadLine() ?? string.Empty) != string.Empty)
                {
                    var transactionType = Convert.ToInt32(line[..1]);

                    if (!transactionTypesIds.Contains(transactionType))
                    {
                        throw new InvalidTransactionTypeException();
                    }

                    var year = Convert.ToInt32(line.Substring(1, 4));
                    var month = Convert.ToInt32(line.Substring(5, 2));
                    var day = Convert.ToInt32(line.Substring(7, 2));
                    var hour = Convert.ToInt32(line.Substring(42, 2));
                    var minute = Convert.ToInt32(line.Substring(44, 2));
                    var second = Convert.ToInt32(line.Substring(46, 2));

                    var value = Convert.ToDecimal(line.Substring(9, 10)) / 100;

                    var taxId = line.Substring(19, 11);
                    var cardNumber = line.Substring(30, 12);
                    var shopOwner = line.Substring(48, 14).Trim();
                    var shopName = line.Substring(62, 18).Trim();


                    var recordTransaction = new Transactions
                    {
                        TransactionTypeID = transactionType,
                        Date = new DateTime(year, month, day, hour, minute, second),
                        Value = value,
                        CPF = taxId,
                        CardNumber = cardNumber,
                        EmporiumOwner = shopOwner,
                        EmporiumName = shopName
                    };

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