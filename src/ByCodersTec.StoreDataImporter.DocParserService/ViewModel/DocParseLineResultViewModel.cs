using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService.ViewModel
{
    public class DocParseLineResultViewModel<T>
    {
        public string LineContent { get; set; }
        public List<IParseDocColumn> Columns { get; set; } = new List<IParseDocColumn>();
        public T ParsedLineItem { get; set; }
        public bool IsValid
        {
            get
            {
                if (Columns?.Any() == true)
                {
                    return Columns.Count(x => !x.IsValid) == 0;
                }
                return true;
            }
        }
    }
}
