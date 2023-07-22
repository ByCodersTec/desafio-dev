namespace ByCodersTec.StoreDataImporter.ViewModel
{
    public enum DocDefinitionColumnTypeEnumViewModel
    {
        String,
        Int,
        Decimal,
        Boolean,
        DateTime
    }
    public class DocColumnViewModel
    {
        public string Name { get; set; }
        public string ClassPropName { get; set; }
        public DocDefinitionColumnTypeEnumViewModel Type { get; set; }
        public int Start { get; set; }
        public int End { get; set; }
        public int Lenght { get; set; }
        public string Description { get; set; }
    }
}