using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Store
{
    public class GetStoreByName
    {
        [Fact]
        public async Task GetStoreByName_Success()
        {
            // Arrange
            var repository = Repository();

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

            await repository.Create(model1);
            await repository.Create(model2);

            // Act
            var store = await repository.GetStoreByName("Store 1");

            // Assert
            Assert.NotNull(store);
            Assert.Equal(model1.Id, store.Id);
            Assert.Equal("Store 1", store.Name);
        }

        [Fact]
        public async Task GetStoreByName_NotFound()
        {
            // Arrange
            var repository = Repository();

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

            await repository.Create(model1);
            await repository.Create(model2);

            // Act
            var store = await repository.GetStoreByName("Store 3");

            // Assert
            Assert.Null(store);
        }

        private static IStoreRepository Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("StoreRepository.GetStoreByName");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new StoreRepository(context);
        }
    }
}
