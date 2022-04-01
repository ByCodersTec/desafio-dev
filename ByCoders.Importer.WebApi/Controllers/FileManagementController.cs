using ByCoders.Importer.WebApi.Services;
using Microsoft.AspNetCore.Mvc;

namespace ByCoders.Importer.WebApi.Controllers
{
    [ApiController, Route("api/[controller]")]
    public class FileManagementController : ControllerBase
    {
        private readonly ITransactionService transactionService;

        public FileManagementController(ITransactionService transactionService)
        {
            this.transactionService = transactionService;
        }


        [HttpPost, Route("[action]"), RequestSizeLimit(4194304)]
        public async Task<IActionResult> Upload()
        {
            try
            {
                if (Request.Form.Files.Any())
                {
                    await transactionService.ImportTransactionsFromFile(Request.Form.Files[0]);
                    return Ok();
                }
                return BadRequest();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}