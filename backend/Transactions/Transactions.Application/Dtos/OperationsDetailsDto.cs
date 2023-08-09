using Transactions.Application.ViewModels;

namespace Transactions.Application.Dtos
{
    public class OperationsDetailsDto
    {
        public string StoreName { get; set; }
        public double Balance { get; set; }
        public IEnumerable<OperationViewModel> Operations { get; set; }
    }
}
