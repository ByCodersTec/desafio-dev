using AutoMapper;
using Microsoft.EntityFrameworkCore;
using Transactions.Application.AutoMapper;
using Transactions.Domain.Enums;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;
using Transactions.Services.Interfaces;
using Transactions.Services.Services;

namespace Transactions.UnitTests.Services.Account
{
    public class GetOperationsByStore
    {
        private static IStoreRepository _storeRepository;
        private static IOperationRepository _operationRepository;

        [Fact]
        public async Task GetOperationsByStore_Success()
        {
            // Arrange
            var service = Service();

            var store = new StoreModel()
            {
                Id = Guid.NewGuid(),
                Name = "Store 1",
                OwnerName = "Owner 1",
                Balance = 10052,
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var operation1 = new OperationModel()
            {
                Id = Guid.NewGuid(),
                StoreId = store.Id,
                TransactionType = TransactionType.Debit,
                Date = DateTime.Now,
                Value = 100,
                Document = "000.000.000-00",
                Card = "1234****1234",
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var operation2 = new OperationModel()
            {
                Id = Guid.NewGuid(),
                StoreId = store.Id,
                TransactionType = TransactionType.BankSlip,
                Date = DateTime.Now,
                Value = 200,
                Document = "111.111.111-11",
                Card = "2345****2345",
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            await _storeRepository.Create(store);

            await _operationRepository.Create(operation1);
            await _operationRepository.Create(operation2);


            // Act
            var operationsDetails = await service.GetOperationsByStore(store.Id);

            // Assert
            Assert.NotNull(operationsDetails);
            Assert.Equal("Store 1", operationsDetails.StoreName);
            Assert.Equal(100.52, operationsDetails.Balance);

            Assert.NotNull(operationsDetails.Operations);
            Assert.NotEmpty(operationsDetails.Operations);
            Assert.Equal(2, operationsDetails.Operations.Count());
        }

        [Fact]
        public async Task GetOperationsByStore_StoreNotFound()
        {
            // Arrange
            var service = Service();

            var store = new StoreModel()
            {
                Id = Guid.NewGuid(),
                Name = "Store 1",
                OwnerName = "Owner 1",
                Balance = 10052,
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var operation1 = new OperationModel()
            {
                Id = Guid.NewGuid(),
                StoreId = store.Id,
                TransactionType = TransactionType.Debit,
                Date = DateTime.Now,
                Value = 100,
                Document = "000.000.000-00",
                Card = "1234****1234",
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var operation2 = new OperationModel()
            {
                Id = Guid.NewGuid(),
                StoreId = store.Id,
                TransactionType = TransactionType.BankSlip,
                Date = DateTime.Now,
                Value = 200,
                Document = "111.111.111-11",
                Card = "2345****2345",
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            await _storeRepository.Create(store);

            await _operationRepository.Create(operation1);
            await _operationRepository.Create(operation2);


            // Act
            var randomStoreId = Guid.NewGuid();

            var operationsDetails = await service.GetOperationsByStore(randomStoreId);

            // Assert
            Assert.Null(operationsDetails);
        }

        [Fact]
        public async Task GetOperationsByStore_NoOperationsFound()
        {
            // Arrange
            var service = Service();

            var store = new StoreModel()
            {
                Id = Guid.NewGuid(),
                Name = "Store 1",
                OwnerName = "Owner 1",
                Balance = 10052,
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var operation1 = new OperationModel()
            {
                Id = Guid.NewGuid(),
                StoreId = Guid.NewGuid(),
                TransactionType = TransactionType.Debit,
                Date = DateTime.Now,
                Value = 100,
                Document = "000.000.000-00",
                Card = "1234****1234",
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var operation2 = new OperationModel()
            {
                Id = Guid.NewGuid(),
                StoreId = Guid.NewGuid(),
                TransactionType = TransactionType.BankSlip,
                Date = DateTime.Now,
                Value = 200,
                Document = "111.111.111-11",
                Card = "2345****2345",
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            await _storeRepository.Create(store);

            await _operationRepository.Create(operation1);
            await _operationRepository.Create(operation2);


            // Act
            var operationsDetails = await service.GetOperationsByStore(store.Id);

            // Assert
            Assert.NotNull(operationsDetails);
            Assert.Equal("Store 1", operationsDetails.StoreName);
            Assert.Equal(100.52, operationsDetails.Balance);

            Assert.NotNull(operationsDetails.Operations);
            Assert.Empty(operationsDetails.Operations);
        }

        private static IAccountService Service()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("AccountService.GetOperationsByStore");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            _storeRepository = new StoreRepository(context);
            _operationRepository = new OperationRepository(context);

            var mapper = new Mapper(AutoMapperSetup.RegisterMapping());

            return new AccountService(_storeRepository, _operationRepository, mapper);
        }
    }
}
