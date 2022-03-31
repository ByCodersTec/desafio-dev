using ByCoders.Importer.Core.BusinessEntities;
using Microsoft.EntityFrameworkCore;

namespace ByCoders.Importer.Core.Repositories.EFCore
{
    public class UserRepository : IUserRepository
    {
        private readonly AppDbContext dbContext;

        public UserRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }


        public async Task<Users> FindByUsernameAsync(string username)
        {
            return await dbContext.Set<Users>()
                .FirstOrDefaultAsync(u => EF.Functions.Like(u.Username, username));
        }
    }
}