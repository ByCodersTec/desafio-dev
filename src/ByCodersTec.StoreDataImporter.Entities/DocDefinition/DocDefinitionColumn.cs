using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public class DocDefinitionColumn : BaseModel<Guid>
    {
        public string Name { get; set; }
        public string ClassPropName { get; set; }
        public DocDefinitionColumnTypeEnum Type { get; set; }
        public int Start { get; set; }
        public int End { get; set; }
        public int Lenght { get; set; }
        public string Description { get; set; }

        public override bool validate()
        {
            return true;
        }
    }
}