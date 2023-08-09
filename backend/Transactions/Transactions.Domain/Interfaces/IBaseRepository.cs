namespace Transactions.Domain.Interfaces
{
    public interface IBaseRepository<TModel> : IDisposable
    {
        Task<List<TModel>> Search();
        Task<TModel> Find(Guid id);
        Task<TModel> Create(TModel model);
        Task CreateMany(List<TModel> models);
        Task Update(TModel model);
        Task Delete(Guid id);
    }
}
