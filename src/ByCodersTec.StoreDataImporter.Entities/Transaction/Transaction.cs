using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public class Transaction : BaseModel<Guid>
    {
        public DateTime Date { get; set; }
        public decimal Value { get; set; }
        public TransactionType Type { get; set; }
        public string Document { get; set; }
        public string Card { get; set; }
        public Store Store { get; set; }

        public override bool validate()
        {
            return true;
        }
    }
}