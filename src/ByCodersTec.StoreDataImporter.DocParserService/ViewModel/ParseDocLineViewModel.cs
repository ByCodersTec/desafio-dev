using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService.ViewModel
{
    public class ParseDocLineViewModel
    {
        public string LineContent { get; set; }
        public List<IParseDocColumn> Columns { get; set; }
        public bool ZeroBased { get; set; }
    }
}
