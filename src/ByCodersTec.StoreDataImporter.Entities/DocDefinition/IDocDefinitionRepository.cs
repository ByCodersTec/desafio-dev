using ByCodersTec.StoreDataImporter.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public interface IDocDefinitionRepository : IBaseRepository<DocDefinition, Guid>
    {
    }

}
