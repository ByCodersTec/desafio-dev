using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public interface IUnitOfWork : IDisposable
    {
        /// <summary>
        /// Call this to commit the unit of work
        /// </summary>
        void Commit();

        /// <summary>
        /// Starts a transaction on this unit of work
        /// </summary>
        void StartTransaction();
    }

}
