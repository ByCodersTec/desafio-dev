using ByCodersTec.StoreDataImporter.DocParserService.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService.Message
{
    public class ParseDocResponse<T>
    {
        public DocParseResultViewModel<T> result { get; set; } = new DocParseResultViewModel<T>();
    }
}
