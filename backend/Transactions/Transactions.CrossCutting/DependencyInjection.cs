using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;
using Transactions.Services.Interfaces;
using Transactions.Services.Services;

namespace Transactions.CrossCutting
{
    public static class DependencyInjection
    {
        public static void AddDependencies(this IServiceCollection services, IConfiguration configuration)
        {
            #region Repositories
            services.AddScoped<IStoreRepository, StoreRepository>();
            services.AddScoped<IOperationRepository, OperationRepository>();
            #endregion

            #region Services
            services.AddScoped<IAccountService, AccountService>();
            services.AddScoped<IFileService, FileService>();
            #endregion

            #region DbContexts
            services.AddDbContext<ApplicationDbContext>(options => options.UseSqlServer(configuration.GetConnectionString("SqlServer")));
            #endregion
        }
    }
}