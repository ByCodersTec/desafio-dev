using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public class PagedResult<T>
    {
        public int CurrentPage { get; private set; }
        public int TotalPages { get; private set; }
        public int PageSize { get; private set; }
        public int TotalCount { get; private set; }
        public PagedList<T> Items { get; set; }

        public bool hasPrivious => CurrentPage > 1;
        public bool hasNext => TotalPages > CurrentPage;

        public PagedResult(PagedList<T> items)
        {
            CurrentPage = items.CurrentPage;
            TotalCount = items.TotalCount;
            TotalPages = items.TotalPages;
            PageSize = items.PageSize;
            Items = items;
        }
    }
}
