using AutoMapper;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Mapping
{
    public static class WithProfile<TProfile> where TProfile : Profile, new()
    {
        private static readonly Lazy<IMapper> MapperFactory = new Lazy<IMapper>(() =>
        {
            var mapperConfig = new MapperConfiguration(config => config.AddProfile<TProfile>());
            return new Mapper(mapperConfig, ServiceCtor);
        });

        public static IMapper Mapper => MapperFactory.Value;

        public static Func<Type, object> ServiceCtor { get; set; }

        public static TDestination Map<TDestination>(object source)
        {
            return Mapper.Map<TDestination>(source);
        }
    }

    public static class DapendenceInjectionSetup
    {
        public static IServiceCollection RegisterAutomapper(this IServiceCollection services)
        {
            services.AddAutoMapper(AppDomain.CurrentDomain.GetAssemblies());
            return services;
        }
    }

}
