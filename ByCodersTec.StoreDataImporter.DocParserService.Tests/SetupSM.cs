using StructureMap;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.DocParserService.Tests
{
    public sealed class StructureMapContainer
    {
        public Container container;
        private StructureMapContainer() {
            container = new Container(_ =>
            {
                _.For<IDocParserService>().Use<DocParserService>();
            });
        }
        private static StructureMapContainer instance = null;
        public static StructureMapContainer Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new StructureMapContainer();
                }
                return instance;
            }
        }
    }
}
