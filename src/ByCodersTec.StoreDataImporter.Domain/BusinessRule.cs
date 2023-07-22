using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public class BusinessRule
    {
        public BusinessRule(string field, string message)
        {
            this.field = field;
            this.message = message;
        }
        public string field { get; set; }
        public string message { get; set; }
    }

}
