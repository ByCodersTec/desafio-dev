namespace Transactions.Application.Dtos
{
    public class FileFormatDto
    {
        public int TransactionType { get; set; }
        public string Date { get; set; }
        public int Value { get; set; }
        public string Document { get; set; }
        public string Card { get; set; }
        public string Time { get; set; }
        public string StoreOwnerName { get; set; }
        public string StoreName { get; set; }
    }
}
