using AutoMapper;
using ByCoders.Importer.Core;
using ByCoders.Importer.Core.BusinessEntities;
using ByCoders.Importer.Core.Identity;
using ByCoders.Importer.Core.Repositories;
using ByCoders.Importer.WebApi.Exceptions;
using ByCoders.Importer.WebApi.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;

namespace ByCoders.Importer.WebApi.Services.Handlers
{
    public class AuthenticationServiceDefault : IAuthenticationService
    {
        private readonly IConfiguration config;
        private readonly AppDbContext dbContext;
        private readonly IMapper mapper;
        private readonly IUserRepository userRepository;
        private readonly IRefreshTokenRepository refreshTokenRepository;

        public AuthenticationServiceDefault(IConfiguration config, AppDbContext dbContext, IMapper mapper, IUserRepository userRepository, IRefreshTokenRepository refreshTokenRepository)
        {
            this.config = config;
            this.dbContext = dbContext;
            this.mapper = mapper;
            this.userRepository = userRepository;
            this.refreshTokenRepository = refreshTokenRepository;
        }


        public async Task<AuthenticationResponseModel> SignInAsync(SignInModel model)
        {
            if (await userRepository.FindByUsernameAsync(model.Username) is Users user
                && PasswordHasher.VerifyHashedPassword(user.PasswordHash, model.Password))
            {
                var authenticationModel = mapper.Map<AuthenticationResponseModel>(user);

                authenticationModel.Token = CreateJwtToken(user);

                var refreshToken = CreateRefreshToken(user);
                await refreshTokenRepository.InsertAsync(refreshToken);
                await dbContext.SaveChangesAsync();

                authenticationModel.RefreshToken = refreshToken.Token;
                authenticationModel.RefreshTokenExpiration = refreshToken.Expiration;

                return authenticationModel;
            }
            throw new InvalidAuthenticationException();
        }

        public async Task<AuthenticationResponseModel> RefreshTokenAsync(RefreshTokenModel model)
        {
            var refreshToken = await refreshTokenRepository.FindByUsernameAndTokenAsync(model.Username, model.RefreshToken);
            if (refreshToken == null)
            {
                throw new RefreshTokenNotFoundException();
            }
            else if (!refreshToken.IsActive)
            {
                throw new InvalidRefreshTokenException();
            }

            using var transaction = dbContext.Database.BeginTransaction();
            try
            {
                var authenticationModel = mapper.Map<AuthenticationResponseModel>(refreshToken.User);

                await refreshTokenRepository.RevokeAsync(refreshToken);

                authenticationModel.Token = CreateJwtToken(refreshToken.User);

                refreshToken = CreateRefreshToken(refreshToken.User);
                await refreshTokenRepository.InsertAsync(refreshToken);
                await dbContext.SaveChangesAsync();

                await transaction.CommitAsync();

                authenticationModel.RefreshToken = refreshToken.Token;
                authenticationModel.RefreshTokenExpiration = refreshToken.Expiration;

                return authenticationModel;
            }
            catch
            {
                await transaction.RollbackAsync();
                throw;
            }
        }


        private string CreateJwtToken(Users user)
        {
            var tokenHandler = new JwtSecurityTokenHandler();

            var token = tokenHandler.CreateToken(new SecurityTokenDescriptor()
            {
                SigningCredentials = new SigningCredentials(
                    new SymmetricSecurityKey(Encoding.UTF8.GetBytes(config["JWT:Key"])),
                    SecurityAlgorithms.HmacSha256
                ),

                Subject = new ClaimsIdentity(
                    new Claim[] {
                        new Claim("uid", user.ID.ToString()),
                        new Claim(ClaimTypes.NameIdentifier, user.Username)
                    }
                ),
                Issuer = config["JWT:Issuer"],
                Audience = config["JWT:Audience"],

                Expires = DateTime.UtcNow.AddMinutes(Convert.ToInt32(config["JWT:DurationInMinutes"]))
            });

            return tokenHandler.WriteToken(token);
        }

        private RefreshTokens CreateRefreshToken(Users user)
        {
            var randomNumber = RandomNumberGenerator.GetBytes(32);

            return dbContext.CreateProxy<RefreshTokens>(x =>
            {
                x.UserID = user.ID;
                x.User = user;
                x.Token = Convert.ToBase64String(randomNumber);
                x.Expiration = DateTime.UtcNow.AddDays(Convert.ToInt32(config["JWT:RefreshTokenExpiration"]));
                x.Created = DateTime.UtcNow;
            });
        }
    }
}