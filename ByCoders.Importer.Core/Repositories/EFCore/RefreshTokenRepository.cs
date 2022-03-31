using ByCoders.Importer.Core.BusinessEntities;
using Microsoft.EntityFrameworkCore;

namespace ByCoders.Importer.Core.Repositories.EFCore
{
    public class RefreshTokenRepository : IRefreshTokenRepository
    {

        private readonly AppDbContext dbContext;

        public RefreshTokenRepository(AppDbContext dbContext)
        {
            this.dbContext = dbContext;
        }


        public async Task InsertAsync(RefreshTokens token)
        {
            await dbContext.Set<RefreshTokens>().AddAsync(token);
        }

        public async Task RevokeAsync(RefreshTokens token)
        {
            token.Revoked = DateTime.UtcNow;
            await Task.Run(() => { dbContext.Set<RefreshTokens>().Update(token); });
        }


        public async Task<RefreshTokens> FindByUsernameAndTokenAsync(string username, string refreshToken)
        {
            return await dbContext.Set<RefreshTokens>()
                .FirstOrDefaultAsync(t => EF.Functions.Like(t.User.Username, username) && EF.Functions.Like(t.Token, refreshToken));
        }
    }
}