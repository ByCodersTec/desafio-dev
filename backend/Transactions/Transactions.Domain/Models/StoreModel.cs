namespace Transactions.Domain.Models
{
    public class StoreModel : BaseModel
    {
        public string Name { get; set; }
        public string OwnerName { get; set; }
        public int Balance { get; set; }

    }
}
