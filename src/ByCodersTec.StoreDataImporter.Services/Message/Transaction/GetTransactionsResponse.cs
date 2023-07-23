using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Message
{
    public class GetTransactionsResponse
    {
        public PagedList<TransactionViewModel> transaction { get; set; }
    }
}
