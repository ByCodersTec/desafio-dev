using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Repository.EF;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Mapping;
using ByCodersTec.StoreDataImporter.Services.Message;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Implementation
{
    public class UserService : IUserService
    {
        readonly IUserRepository _userRepository;
        readonly IUnitOfWork _unitOfWork;
        public UserService(
            IUserRepository userRepository,
            IUnitOfWork unitOfWork
        )
        {
            _userRepository = userRepository;
            _unitOfWork = unitOfWork;
        }

        public AddUserResponse AddUser(AddUserRequest request)
        {
            var user = new User
            {
                createdAt = request.model.createdAt,
                Email = request.model.Email,
                Name = request.model.Name,
                Password = request.model.Password,
                Username = request.model.Username
            };
            _userRepository.Insert(user);
            _unitOfWork.Commit();

            return new AddUserResponse
            {
                user = new ViewModel.UserViewModel
                {
                    createdAt = user.createdAt,
                    Email = user.Email,
                    Id = user.Id,
                    Name = user.Name,
                    Password = user.Password,
                    updatedAt = user.createdAt,
                    Username = user.Username
                }
            };
        }

        public GetUserResponse GetUser(GetUserRequest request)
        {
            var user = _userRepository.Single(request.Id);
            return new GetUserResponse
            {
                user = new ViewModel.UserViewModel
                {
                    createdAt = user.createdAt,
                    Email = user.Email,
                    Id = user.Id,
                    Name = user.Name,
                    Password = user.Password,
                    updatedAt = user.createdAt,
                    Username = user.Username
                }
            };
        }

        public GetUsersResponse GetUsers(GetUsersRequest request)
        {
            var users = _userRepository.GetAll().Select(user => new ViewModel.UserViewModel
            {
                createdAt = user.createdAt,
                Email = user.Email,
                Id = user.Id,
                Name = user.Name,
                Password = user.Password,
                updatedAt = user.createdAt,
                Username = user.Username
            }).ToList();

            return new GetUsersResponse
            {
                list = users
            };
        }
    }
}
