using ByCodersTec.StoreDataImporter.CQRS.Queries;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.ViewModel;
using MediatR;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.CQRS.Handlers
{
    public class GetUsersHandler : IRequestHandler<GetUsersQuery, List<UserViewModel>>
    {
        private readonly IUserRepository _userRepository;
        public GetUsersHandler(IUserRepository userRepository)
        {
            _userRepository = userRepository;
        }
        public Task<List<UserViewModel>> Handle(GetUsersQuery request, CancellationToken cancellationToken)
        {
            var users = _userRepository.GetAll().Select(user => new UserViewModel
            {
                createdAt = user.createdAt,
                Email = user.Email,
                Id = user.Id,
                Name = user.Name,
                Password = user.Password,
                updatedAt = user.createdAt,
                Username = user.Username
            }).ToList();
            return Task.FromResult<List<UserViewModel>>(users);
        }
    }
}
