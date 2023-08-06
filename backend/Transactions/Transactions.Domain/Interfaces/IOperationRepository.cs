﻿using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;

namespace Transactions.Services.Interfaces
{
    public interface IOperationRepository : IBaseRepository<OperationModel>
    {
        Task<List<object>> GetOperationsByStore(string storeId);

        Task CreateOperation();
    }
}
