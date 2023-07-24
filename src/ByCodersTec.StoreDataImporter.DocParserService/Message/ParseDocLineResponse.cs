using ByCodersTec.StoreDataImporter.DocParserService.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService.Message
{
    public class ParseDocLineResponse<T>
    {
        public DocParseLineResultViewModel<T> result { get; set; }
    }
}
