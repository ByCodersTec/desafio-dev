using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.ViewModel;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.CQRS.Queries
{
    public class GetUsersQuery : IRequest<List<UserViewModel>>
    {
        IEnumerable<UserViewModel> users;
    }
}
