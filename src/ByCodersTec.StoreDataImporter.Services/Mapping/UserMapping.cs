using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Mapping
{
    public static class UserMapping
    {
        public static UserViewModel ToUserViewModel(this User entity)
        {
            return WithProfile<UserProfile>.Map<UserViewModel>(entity);
        }
    }
}
