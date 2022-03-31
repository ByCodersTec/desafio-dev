using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.Core.Repositories
{
    public interface IUserRepository
    {
        Task<Users> FindByUsernameAsync(string username);
    }
}