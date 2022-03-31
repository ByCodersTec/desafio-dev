using ByCoders.Importer.WebApi.Exceptions;
using ByCoders.Importer.WebApi.Models;
using ByCoders.Importer.WebApi.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace ByCoders.Importer.WebApi.Controllers
{
    [ApiController, Route("api/[controller]")]
    public class AuthenticationController : ControllerBase
    {
        private readonly IAuthenticationService authenticationService;

        public AuthenticationController(IAuthenticationService authenticationService)
        {
            this.authenticationService = authenticationService;
        }


        [HttpPost, Route("[action]"), AllowAnonymous]
        public async Task<IActionResult> SignIn(SignInModel model)
        {
            try
            {
                return Ok(await authenticationService.SignInAsync(model));
            }
            catch (InvalidAuthenticationException)
            {
                return Unauthorized();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost, Route("[action]"), AllowAnonymous]
        public async Task<IActionResult> RefreshToken(RefreshTokenModel model)
        {
            try
            {
                return Ok(await authenticationService.RefreshTokenAsync(model));
            }
            catch (InvalidRefreshTokenException)
            {
                return Unauthorized();
            }
            catch (RefreshTokenNotFoundException)
            {
                return Unauthorized();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost, Route("[action]")]
        public IActionResult Logout()
        {
            Response.Cookies.Delete("AuthCookie");
            return Ok();
        }
    }
}
