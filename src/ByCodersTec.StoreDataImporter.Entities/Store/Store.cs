using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public class Store : BaseModel<Guid>
    {
        public string Name { get; set; }

        public override bool validate()
        {
            return true;
        }
    }
}