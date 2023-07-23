using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public class PagedList<T> : List<T>
    {
        public int CurrentPage { get; private set; }
        public int TotalPages { get; private set; }
        public int PageSize { get; private set; }
        public int TotalCount { get; private set; }

        public bool hasPrivious => CurrentPage > 1;
        public bool hasNext => TotalPages > CurrentPage;
        public PagedList(List<T> items, int count, int pageNumber, int pagesize)
        {
            TotalCount = count;
            PageSize = pagesize;
            CurrentPage = pageNumber;
            TotalPages = (int)Math.Ceiling((double)count / pagesize);
            AddRange(items);
        }
    }
}
