using ByCodersTec.StoreDataImporter.Services.Message;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services.Interfaces
{
    public interface ITransactionService
    {
        Task<AddTransactionsFromFileResponse> AddTransactionsCNABFromFile(AddTransactionsFromFileRequest request);
        AddTransactionResponse AddTransaction(AddTransactionRequest request);
        GetTransactionsResponse GetTransactions(GetTransactionsRequest request);
        Task<ValidateTransactionsFromFileResponse> ValidateFile(ValidateTransactionsFromFileRequest request);
    }
}
