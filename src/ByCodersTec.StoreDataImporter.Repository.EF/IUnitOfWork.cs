using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF
{
    public interface IUnitOfWork : Domain.IUnitOfWork, IDisposable
    {
        /// <summary>
        /// Return the database reference for this UOW
        /// </summary>
        DbContext Db { get; }
    }

}
