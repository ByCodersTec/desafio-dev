using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Base
{
    public class Search
    {
        [Fact]
        public async Task Search_SuccessWithContent()
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
            var stores = await repository.Search();

            // Assert
            Assert.NotNull(stores);
            Assert.Equal(2, stores.Count);
        }

        [Fact]
        public async Task Search_SuccessWithNoContent()
        {
            // Arrange
            var repository = Repository();

            // Act
            var stores = await repository.Search();

            // Assert
            Assert.NotNull(stores);
            Assert.Empty(stores);
        }

        private static IBaseRepository<StoreModel> Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("BaseRepository.Search");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new BaseRepository<StoreModel>(context);
        }
    }
}
