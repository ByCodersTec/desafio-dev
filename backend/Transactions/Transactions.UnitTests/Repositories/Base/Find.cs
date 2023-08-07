using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Base
{
    public class Find
    {
        [Fact]
        public async Task Find_Success()
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

            await repository.CreateMany(new List<StoreModel>() { model1, model2 });

            // Act
            var store = await repository.Find(model1.Id);

            // Assert
            Assert.NotNull(store);
            Assert.Equal("Store 1", store.Name);
        }

        [Fact]
        public async Task Find_WithModelNotFound()
        {
            // Arrange
            var repository = Repository();

            var model = new StoreModel()
            {
                Id = Guid.NewGuid(),
                Name = "Store 1",
                OwnerName = "Owner 1",
                Balance = 100,
                CreatedAt = DateTime.Now,
                UpdatedAt = DateTime.Now
            };

            var notFoundId = Guid.NewGuid();

            await repository.Create(model);

            // Act
            var store = await repository.Find(notFoundId);

            // Assert
            Assert.Null(store);
        }

        private static IBaseRepository<StoreModel> Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("BaseRepository.Find");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new BaseRepository<StoreModel>(context);
        }
    }
}
