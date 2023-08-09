using AutoMapper;
using Microsoft.EntityFrameworkCore;
using Transactions.Application.AutoMapper;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;
using Transactions.Services.Interfaces;
using Transactions.Services.Services;

namespace Transactions.UnitTests.Services.Account
{
    public class GetStores
    {
        private static IStoreRepository _storeRepository;
        private static IOperationRepository _operationRepository;

        [Fact]
        public async Task GetStores_Success()
        {
            // Arrange
            var service = Service();

            var model1 = new StoreModel()
            {
                Id = Guid.NewGuid(),
                Name = "Store 1",
                OwnerName = "Owner 1",
                Balance = 100,
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var model2 = new StoreModel()
            {
                Id = Guid.NewGuid(),
                Name = "Store 2",
                OwnerName = "Owner 2",
                Balance = 200,
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            await _storeRepository.Create(model1);
            await _storeRepository.Create(model2);

            // Act
            var stores = await service.GetStores();

            // Assert
            Assert.NotNull(stores);
            Assert.NotEmpty(stores);
            Assert.Equal(2, stores.Count);
        }

        [Fact]
        public async Task GetStores_Empty()
        {
            // Arrange
            var service = Service();

            // Act
            var stores = await service.GetStores();

            // Assert
            Assert.NotNull(stores);
            Assert.Empty(stores);
        }

        private static IAccountService Service()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("AccountService.GetStores");
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
