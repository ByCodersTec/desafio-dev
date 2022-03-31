using ByCoders.Importer.WebApi.Models;

namespace ByCoders.Importer.WebApi.Services
{
    public interface IAuthenticationService
    {
        Task<AuthenticationResponseModel> SignInAsync(SignInModel model);

        Task<AuthenticationResponseModel> RefreshTokenAsync(RefreshTokenModel model);
    }
}