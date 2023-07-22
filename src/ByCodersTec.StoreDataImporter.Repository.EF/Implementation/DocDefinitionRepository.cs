using ByCodersTec.StoreDataImporter.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF.Implementation
{
    public class DocDefinitionRepository : BaseRepository<DocDefinition, Guid>, IDocDefinitionRepository
    {
        public DocDefinitionRepository(IUnitOfWork unit) : base(unit)
        {

        }
    }

}
