using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public class DocDefinition : BaseModel<Guid>
    {
        public string Name { get; set; }
        public string Code { get; set; }
        public virtual ICollection<DocDefinitionColumn> Columns { get; set; }

        public override bool validate()
        {
            return true;
        }
    }
}