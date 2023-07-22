using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Transactions;

namespace ByCodersTec.StoreDataImporter.Repository.EF
{
    public class UnitOfWork : IUnitOfWork
    {
        private TransactionScope _transaction;
        private readonly AppContext _db;

        public UnitOfWork(IAppContext appContext)
        {
            _db = (AppContext)appContext;
        }

        public void Dispose()
        {

        }

        public void StartTransaction()
        {
            _transaction = new TransactionScope();
        }

        public void Commit()
        {
            _db.SaveChanges();
            if (_transaction != null)
                _transaction.Complete();
        }

        public DbContext Db
        {
            get { return _db; }
        }
    }

}
