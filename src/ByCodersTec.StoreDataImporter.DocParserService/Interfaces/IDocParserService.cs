using ByCodersTec.StoreDataImporter.DocParserService.Message;
using ByCodersTec.StoreDataImporter.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService
{
    public interface IDocParserService
    {
        ParseDocLineResponse<T> ParseFileLinseFromString<T>(ParseDocLineRequest request);
        ParseDocResponse<T> ParseFileLinseFromString<T>(ParseDocRequest request);
    }
}
