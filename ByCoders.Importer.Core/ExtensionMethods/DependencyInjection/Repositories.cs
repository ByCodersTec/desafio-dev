using ByCoders.Importer.Core.Repositories;
using ByCoders.Importer.Core.Repositories.EFCore;
using Microsoft.Extensions.DependencyInjection;

namespace ByCoders.Importer.Core.ExtensionMethods.DependencyInjection
{
    public static class Repositories
    {
        public static IServiceCollection AddRepositories(this IServiceCollection services)
        {
            return services
                .AddScoped<IRefreshTokenRepository, RefreshTokenRepository>()
                .AddScoped<IUserRepository, UserRepository>()
                .AddScoped<ITransactionRepository, TransactionRepository>()
                ;
        }
    }
}