using AutoMapper;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Mapping
{
    public class UserProfile : Profile
    {
        public UserProfile()
        {
            CreateMap<User, UserViewModel>();
        }
    }
}
