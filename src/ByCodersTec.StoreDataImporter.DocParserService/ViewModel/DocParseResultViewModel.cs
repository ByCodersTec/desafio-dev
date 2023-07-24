using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService.ViewModel
{
    public class DocParseResultViewModel<T>
    {
        public List<DocParseLineResultViewModel<T>> Lines { get; set; } = new List<DocParseLineResultViewModel<T>>();
    }
}
