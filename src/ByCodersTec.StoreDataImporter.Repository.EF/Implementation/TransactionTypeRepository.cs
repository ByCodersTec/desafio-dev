using ByCodersTec.StoreDataImporter.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF.Implementation
{
    public class TransactionTypeRepository : BaseRepository<TransactionType, Guid>, ITransactionTypeRepository
    {
        public TransactionTypeRepository(IUnitOfWork unit) : base(unit)
        {

        }
    }

}
