namespace CNAB.Core.Data
{
    public interface IUnitOfWork : IDisposable
    {
        Task<int> SaveChanges();
        Task BeginTransactionAsync();
        Task CommitAsync();
        void Rollback();

    }
}
