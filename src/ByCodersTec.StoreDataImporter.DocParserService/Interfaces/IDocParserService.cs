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
        T ParseFileLinseFromString<T>(string fileLine, List<DocColumnViewModel> columns, bool zeroBased);
        List<T> ParseFileLinseFromString<T>(List<string> fileLine, List<DocColumnViewModel> columns, bool zeroBased);
    }
}
