using ByCoders.Importer.WebApi.Services;
using ByCoders.Importer.WebApi.Services.Handlers;

namespace ByCoders.Importer.WebApi.ExtensionMethods.DependencyInjection
{
    public static class Services
    {
        public static IServiceCollection AddWebAPIServices(this IServiceCollection services)
        {
            return services
                .AddScoped<IAuthenticationService, AuthenticationServiceDefault>()
                ;
        }
    }
}