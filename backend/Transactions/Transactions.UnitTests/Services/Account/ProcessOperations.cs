using AutoMapper;
using Microsoft.EntityFrameworkCore;
using Transactions.Application.AutoMapper;
using Transactions.Application.Dtos;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;
using Transactions.Services.Interfaces;
using Transactions.Services.Services;

namespace Transactions.UnitTests.Services.Account
{
    public class ProcessOperations
    {
        private static IStoreRepository _storeRepository;
        private static IOperationRepository _operationRepository;

        [Fact]
        public async Task ProcessOperations_Success()
        {
            // Arrange
            var service = Service();

            var operation1 = new FileFormatDto()
            {
                TransactionType = 1,
                Date = "2023-07-07",
                Value = 12345,
                Document = "000.000.000-00",
                Card = "1234****1234",
                Time = "14:00:00",
                StoreOwnerName = "Owner 1",
                StoreName = "Store 1"
            };

            var operation2 = new FileFormatDto()
            {
                TransactionType = 2,
                Date = "2023-08-08",
                Value = 23456,
                Document = "111.111.111-11",
                Card = "2345****2345",
                Time = "07:00:00",
                StoreOwnerName = "Owner 2",
                StoreName = "Store 2"
            };

            var operation3 = new FileFormatDto()
            {
                TransactionType = 3,
                Date = "2023-08-09",
                Value = 34567,
                Document = "111.111.111-11",
                Card = "2345****2345",
                Time = "08:00:00",
                StoreOwnerName = "Owner 2",
                StoreName = "Store 2"
            };

            var operations = new List<FileFormatDto>() { operation1, operation2, operation3 };

            // Act
            await service.ProcessOperations(operations);

            var storeList = await _storeRepository.Search();
            var operationList = await _operationRepository.Search();

            // Assert
            Assert.NotNull(storeList);
            Assert.NotEmpty(storeList);
            Assert.Equal(2, storeList.Count);

            Assert.NotNull(operationList);
            Assert.NotEmpty(operationList);
            Assert.Equal(3, operationList.Count);
        }

        [Fact]
        public async Task ProcessOperations_CheckStoreBalance()
        {
            // Arrange
            var service = Service();

            var operation1 = new FileFormatDto()
            {
                TransactionType = 1,
                Date = "2023-07-07",
                Value = 12345,
                Document = "000.000.000-00",
                Card = "1234****1234",
                Time = "14:00:00",
                StoreOwnerName = "Owner 1",
                StoreName = "Store 1"
            };

            var operation2 = new FileFormatDto()
            {
                TransactionType = 2,
                Date = "2023-08-08",
                Value = 23456,
                Document = "111.111.111-11",
                Card = "2345****2345",
                Time = "07:00:00",
                StoreOwnerName = "Owner 2",
                StoreName = "Store 2"
            };

            var operation3 = new FileFormatDto()
            {
                TransactionType = 3,
                Date = "2023-08-09",
                Value = 34567,
                Document = "111.111.111-11",
                Card = "2345****2345",
                Time = "08:00:00",
                StoreOwnerName = "Owner 2",
                StoreName = "Store 2"
            };

            var operations = new List<FileFormatDto>() { operation1, operation2, operation3 };

            // Act
            await service.ProcessOperations(operations);

            var store1 = await _storeRepository.GetStoreByName("Store 1");
            var store2 = await _storeRepository.GetStoreByName("Store 2");

            // Assert
            Assert.NotNull(store1);
            Assert.Equal(12345, store1.Balance);

            Assert.NotNull(store2);
            Assert.Equal(-58023, store2.Balance);

        }

        [Fact]
        public async Task ProcessOperations_InvalidTransactionTypeDoNotAffectStoreBalance()
        {
            // Arrange
            var service = Service();

            var operation1 = new FileFormatDto()
            {
                TransactionType = 0,
                Date = "2023-07-07",
                Value = 12345,
                Document = "000.000.000-00",
                Card = "1234****1234",
                Time = "14:00:00",
                StoreOwnerName = "Owner 1",
                StoreName = "Store 1"
            };

            var operation2 = new FileFormatDto()
            {
                TransactionType = 0,
                Date = "2023-08-08",
                Value = 23456,
                Document = "111.111.111-11",
                Card = "2345****2345",
                Time = "07:00:00",
                StoreOwnerName = "Owner 2",
                StoreName = "Store 2"
            };

            var operation3 = new FileFormatDto()
            {
                TransactionType = 0,
                Date = "2023-08-09",
                Value = 34567,
                Document = "111.111.111-11",
                Card = "2345****2345",
                Time = "08:00:00",
                StoreOwnerName = "Owner 2",
                StoreName = "Store 2"
            };

            var operations = new List<FileFormatDto>() { operation1, operation2, operation3 };

            // Act
            await service.ProcessOperations(operations);

            var store1 = await _storeRepository.GetStoreByName("Store 1");
            var store2 = await _storeRepository.GetStoreByName("Store 2");

            // Assert
            Assert.NotNull(store1);
            Assert.Equal(0, store1.Balance);

            Assert.NotNull(store2);
            Assert.Equal(0, store2.Balance);
        }

        private static IAccountService Service()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("AccountService.ProcessOperations");
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
