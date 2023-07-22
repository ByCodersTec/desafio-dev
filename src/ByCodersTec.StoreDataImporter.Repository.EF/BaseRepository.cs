using ByCodersTec.StoreDataImporter.Domain;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF
{
    public class BaseRepository<T, TId> : IBaseRepository<T, TId>
        where T : class
    {
        private readonly IUnitOfWork _unitOfWork;
        internal DbSet<T> dbSet;

        public BaseRepository(IUnitOfWork unitOfWork)
        {
            if (unitOfWork == null) throw new ArgumentNullException("unitOfWork");
            _unitOfWork = unitOfWork;
            this.dbSet = _unitOfWork.Db.Set<T>();
        }

        /// <summary>
        /// Returns the object with the primary key specifies or throws
        /// </summary>
        /// <typeparam name="TU">The type to map the result to</typeparam>
        /// <param name="primaryKey">The primary key</param>
        /// <returns>The result mapped to the specified type</returns>
        public T Single(TId primaryKey)
        {
            var dbResult = dbSet.Find(primaryKey);
            return dbResult;
        }

        public T SingleOrDefault(TId primaryKey)
        {
            var dbResult = dbSet.Find(primaryKey);
            return dbResult;
        }

        public bool Exists(Expression<Func<T, bool>> filter = null, Func<IQueryable<T>, IOrderedQueryable<T>> orderBy = null, string includeProperties = "")
        {
            IQueryable<T> query = this.dbSet;
            if (filter != null)
            {
                query = query.Where(filter);
            }

            foreach (var includeProperty in includeProperties.Split
                (new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries))
            {
                query = query.Include(includeProperty);
            }

            if (orderBy != null)
            {
                return orderBy(query).ToList().Count > 0;
            }
            else
            {
                return query.ToList().Count > 0;
            }
        }

        public virtual object Insert(IBaseModel entity)
        {
            DateTime dt = DateTime.Now;
            entity.createdAt = dt;
            entity.updatedAt = dt;

            dynamic obj = dbSet.Add((T)entity);
            this._unitOfWork.Db.SaveChanges();
            return obj;
        }

        public virtual void Update(IBaseModel entity)
        {
            entity.updatedAt = DateTime.Now;
            dbSet.Attach((T)entity);
            _unitOfWork.Db.Entry(entity).State = EntityState.Modified;
            this._unitOfWork.Db.SaveChanges();
        }

        public object Delete(T entity)
        {
            if (_unitOfWork.Db.Entry(entity).State == EntityState.Detached)
            {
                dbSet.Attach(entity);
            }
            dynamic obj = dbSet.Remove(entity);
            this._unitOfWork.Db.SaveChanges();
            return obj;
        }

        public IUnitOfWork UnitOfWork { get { return _unitOfWork; } }
        internal DbContext Database { get { return _unitOfWork.Db; } }

        Domain.IUnitOfWork IBaseRepository<T, TId>.UnitOfWork => throw new NotImplementedException();

        public IEnumerable<T> GetAll(Expression<Func<T, bool>> filter, Func<IQueryable<T>, IOrderedQueryable<T>> orderBy, string includeProperties, string includeSecondProperties)
        {
            IQueryable<T> query = this.dbSet;

            if (filter != null)
            {
                query = query.Where(filter);
            }

            if (!string.IsNullOrEmpty(includeProperties))
            {
                foreach (var includeProperty in includeProperties.Split
                (new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries))
                {
                    if (!string.IsNullOrEmpty(includeSecondProperties))
                    {
                        query = query.Include(includeProperty).Include(includeSecondProperties);
                    }
                    else
                    {
                        query = query.Include(includeProperty);
                    }
                }
            }

            if (orderBy != null)
            {
                return orderBy(query).ToList();
            }
            else
            {
                return query.ToList();
            }
        }

        public Dictionary<string, string> GetAuditNames(dynamic dynamicObject)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<T> GetAll()
        {
            return this.GetAll(null, null, null, null);
        }

        public IEnumerable<T> GetAll(Expression<Func<T, bool>> filter)
        {
            return this.GetAll(filter, null, null, null);
        }

        public IEnumerable<T> GetAll(Func<IQueryable<T>, IOrderedQueryable<T>> orderBy)
        {
            return this.GetAll(null, orderBy, null, null);
        }

        public IEnumerable<T> GetAll(string includeProperties, string includeSecondProperties)
        {
            return this.GetAll(null, null, includeSecondProperties, null);
        }

        public IEnumerable<T> GetAll(Expression<Func<T, bool>> filter, Func<IQueryable<T>, IOrderedQueryable<T>> orderBy)
        {
            return this.GetAll(filter, orderBy, null, null);
        }
    }

}
