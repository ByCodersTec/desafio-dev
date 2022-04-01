namespace ByCoders.Importer.WebApi.Services
{
    public interface ITransactionService
    {
        Task ImportTransactionsFromFile(IFormFile file);
    }
}