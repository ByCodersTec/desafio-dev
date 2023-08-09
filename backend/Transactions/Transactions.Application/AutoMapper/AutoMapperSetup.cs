using AutoMapper;

namespace Transactions.Application.AutoMapper
{
    public class AutoMapperSetup
    {
        public static MapperConfiguration RegisterMapping()
        {
            return new MapperConfiguration(configuration =>
            {
                configuration.AddProfile(new ModelToViewModelMappingProfile());
            });
        }
    }
}