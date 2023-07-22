using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public enum TransactionNature
    {
        Entrada,
        Saida
    }
    public class TransactionType : BaseModel<Guid>
    {
        public string Description { get; set; }
        public int Code { get; set; }
        public TransactionNature Nature { get; set; }
        public override bool validate()
        {
            return true;
        }
    }
}