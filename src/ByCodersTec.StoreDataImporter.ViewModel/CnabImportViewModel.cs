using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.ViewModel
{
    public class CnabImportViewModel
    {
        public int Type { get; set; }
        public string Date { get; set; }
        public string Val { get; set; }
        public string Document { get; set; }
        public string Card { get; set; }
        public string Time { get; set; }
        public string Dealer { get; set; }
        public string StoreName { get; set; }
        public string LineHash
        {
            get
            {
                return string.Format("{0}{1}{2}{4}{5}{6}", Document, Card, Date, Time, Val, Dealer, StoreName).HashString();
            }
        }
    }
}