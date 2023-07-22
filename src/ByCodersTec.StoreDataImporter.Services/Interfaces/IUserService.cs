using ByCodersTec.StoreDataImporter.Services.Message;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Interfaces
{
    public interface IUserService
    {
        GetUserResponse GetUser(GetUserRequest request);
        GetUsersResponse GetUsers(GetUsersRequest request);
        AddUserResponse AddUser(AddUserRequest request);
    }
}
