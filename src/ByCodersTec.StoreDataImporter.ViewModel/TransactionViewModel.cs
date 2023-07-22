namespace ByCodersTec.StoreDataImporter.ViewModel
{
    public class TransactionViewModel
    {
        public DateTime Date { get; set; }
        public decimal Value { get; set; }
        public int Type { get; set; }
        public string Document { get; set; }
        public string Card { get; set; }
        public string StoreName { get; set; }
        public string Identifier { get; set; }
    }
}