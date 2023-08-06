namespace Transactions.Application.ViewModels
{
    public class OperationViewModel : BaseViewModel
    {
        public string Transaction { get; set; }
        public DateTime Date { get; set; }
        public double Value { get; set; }
        public string Document { get; set; }
        public string Card { get; set; }
    }
}
