using Transactions.Domain.Enums;

namespace Transactions.Domain.Models
{
    public class OperationModel : BaseModel
    {
        public Guid StoreId { get; set; }
        public TransactionType TransactionType { get; set; }
        public DateTime Date { get; set; }
        public int Value { get; set; }
        public string Document { get; set; }
        public string Card { get; set; }
    }
}
