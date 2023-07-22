using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.ViewModel;
using Microsoft.AspNetCore.Mvc;

namespace ByCodersTec.StoreDataImporter.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UsersController : ControllerBase
    {
        private readonly ILogger<UsersController> _logger;
        readonly IUserService _userService;
        readonly IMessageService _messageService;
        public UsersController(
            ILogger<UsersController> logger,
            IUserService userService,
            IMessageService messageService
        )
        {
            _logger = logger;
            _userService = userService;
            _messageService = messageService;
        }

        [HttpGet("")]
        public ApiResponse<List<UserViewModel>> GetUsers()
        {
            var user = _userService.GetUsers(new Services.Message.GetUsersRequest { }).list;
            return ApiResponse<List<UserViewModel>>.CreateResponse(user);
        }

        [HttpGet("{id}")]
        public ApiResponse<UserViewModel> GetUser(Guid id)
        {
            var user = _userService.GetUser(new Services.Message.GetUserRequest { Id = id }).user;
            return ApiResponse<UserViewModel>.CreateResponse(user);
        }

        [HttpPost("enqueue")]
        public ApiResponse<bool> EnqueueUser([FromBody] UserViewModel model)
        {
            var userenqueued = _messageService.Enqueue(model);
            return ApiResponse<bool>.CreateResponse(userenqueued);
        }
    }
}