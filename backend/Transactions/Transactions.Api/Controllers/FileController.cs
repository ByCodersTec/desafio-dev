using Microsoft.AspNetCore.Mvc;
using Transactions.Services.Interfaces;

namespace Transactions.Api.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class FileController : ControllerBase
    {
        protected readonly IFileService _fileService;

        public FileController(IFileService fileService)
        {
            _fileService = fileService;
        }

        [HttpPost("import")]
        public async Task ImportFile(IFormFile file)
        {
            if (file == null)
            {
                return;
            }

            await _fileService.ProcessFile(file);
        }
    }
}