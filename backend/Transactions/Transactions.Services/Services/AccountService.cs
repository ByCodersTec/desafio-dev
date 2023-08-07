using AutoMapper;
using Transactions.Application.Dtos;
using Transactions.Application.ViewModels;
using Transactions.Domain.Enums;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Services.Interfaces;

namespace Transactions.Services.Services
{
    public class AccountService : IAccountService
    {
        protected readonly IStoreRepository _storeRepository;
        protected readonly IOperationRepository _operationRepository;
        protected readonly IMapper _mapper;

        private readonly static List<int> incomingTransactionTypes = new() { 1, 4, 5, 6, 7, 8 };
        private readonly static List<int> outgoingTransactionTypes = new() { 2, 3, 9 };

        public AccountService(IStoreRepository storeRepository, IOperationRepository operationRepository, IMapper mapper)
        {
            _storeRepository = storeRepository;
            _operationRepository = operationRepository;
            _mapper = mapper;
        }

        public async Task<List<StoreViewModel>> GetStores()
        {
            var stores =  await _storeRepository.Search();

            return _mapper.Map<List<StoreViewModel>>(stores);
        }

        public async Task<OperationsDetailsDto> GetOperationsByStore(Guid storeId)
        {
            var store = await _storeRepository.Find(storeId);

            if (store == null)
            {
                return null;
            }

            var operations = await _operationRepository.GetOperationsByStore(storeId);

            return new OperationsDetailsDto()
            {
                StoreName = store.Name,
                Balance = Convert.ToDouble(store.Balance) / 100,
                Operations = _mapper.Map<List<OperationViewModel>>(operations.OrderByDescending(x => x.Date))
            };
        }

        public async Task ProcessOperations(List<FileFormatDto> operations)
        {
            foreach (var operation in operations)
            {
                var operationModel = new OperationModel()
                {
                    Id = Guid.NewGuid(),
                    TransactionType = (TransactionType)operation.TransactionType,
                    Date = DateTime.Parse($"{operation.Date} {operation.Time}"),
                    Value = operation.Value,
                    Document = operation.Document,
                    Card = operation.Card,
                    CreatedAt = DateTime.Now,
                    UpdatedAt = DateTime.Now
                };

                var storeModel = await _storeRepository.GetStoreByName(operation.StoreName);

                if (storeModel == null)
                {
                    storeModel = new StoreModel()
                    {
                        Id = Guid.NewGuid(),
                        Name = operation.StoreName,
                        OwnerName = operation.StoreOwnerName,
                        Balance = 0,
                        CreatedAt = DateTime.Now,
                        UpdatedAt = DateTime.Now
                    };

                    await _storeRepository.Create(storeModel);
                };

                operationModel.StoreId = storeModel.Id;

                await _operationRepository.Create(operationModel);

                await UpdateStoreBalance(storeModel, operationModel);
            }
        }

        private async Task UpdateStoreBalance(StoreModel storeModel, OperationModel operationModel)
        {
            if (incomingTransactionTypes.Contains((int)operationModel.TransactionType))
            {
                storeModel.Balance += operationModel.Value;
            }
            else if (outgoingTransactionTypes.Contains((int)operationModel.TransactionType))
            {
                storeModel.Balance -= operationModel.Value;
            }

            await _storeRepository.Update(storeModel);
        }
    }
}
