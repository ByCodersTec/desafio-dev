using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public interface IMessageService
    {
        bool Enqueue<T>(T message, Dictionary<string, object> props);
    }
}
