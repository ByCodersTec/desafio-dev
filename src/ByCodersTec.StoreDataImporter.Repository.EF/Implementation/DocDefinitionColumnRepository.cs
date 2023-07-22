using ByCodersTec.StoreDataImporter.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF.Implementation
{
    public class DocDefinitionColumnRepository : BaseRepository<DocDefinitionColumn, Guid>, IDocDefinitionColumnRepository
    {
        public DocDefinitionColumnRepository(IUnitOfWork unit) : base(unit)
        {

        }
    }

}
