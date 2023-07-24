using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public interface IParseDocColumn
    {
        public bool IsValid { get; set; }
        public string ValidationPattern { get; set; }
        public string ClassPropName { get; set; }
        public int Length { get; set; }
        public int Start { get; set; }
        public int End { get; set; }
    }
}
