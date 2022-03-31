using AutoMapper;
using AutoMapper.Configuration.Annotations;
using ByCoders.Importer.Core.BusinessEntities;

namespace ByCoders.Importer.WebApi.Models
{
    [AutoMap(typeof(Users), ReverseMap = false)]
    public class AuthenticationResponseModel
    {
        public string Username { get; set; }
        public string Name { get; set; }

        [Ignore]
        public string Token { get; set; }

        [Ignore]
        public string RefreshToken { get; set; }

        [Ignore]
        public DateTime RefreshTokenExpiration { get; set; }
    }
}