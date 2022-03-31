using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.Core.Repositories
{
    public interface IRefreshTokenRepository
    {
        Task InsertAsync(RefreshTokens token);

        Task RevokeAsync(RefreshTokens token);

        Task<RefreshTokens> FindByUsernameAndTokenAsync(string username, string refreshToken);
    }
}