namespace ByCodersTec.StoreDataImporter.ViewModel
{
    public class DocViewModel
    {
        public string Name { get; set; }
        public string Code { get; set; }
        public virtual ICollection<DocColumnViewModel> Columns { get; set; }
    }
}