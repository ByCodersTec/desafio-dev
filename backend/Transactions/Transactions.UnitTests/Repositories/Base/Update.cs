using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Base
{
    public class Update
    {
        [Fact]
        public async Task Update_Success()
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

            var modelUpdated = new StoreModel()
            {
                Id = model.Id,
                Name = "Store 2",
                OwnerName = "Owner 2",
                Balance = 200,
                CreatedAt = model.CreatedAt,
                UpdatedAt = DateTime.Now
            };

            await repository.Create(model);

            // Act
            await repository.Update(modelUpdated);

            var store = await repository.Find(model.Id);

            // Assert
            Assert.NotNull(store);
            Assert.Equal("Store 2", store.Name);
            Assert.Equal("Owner 2", store.OwnerName);
        }

        [Fact]
        public async Task Update_WithModelNotFound()
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

            var modelUpdated = new StoreModel()
            {
                Id = notFoundId,
                Name = "Store 2",
                OwnerName = "Owner 2",
                Balance = 200,
                CreatedAt = model.CreatedAt,
                UpdatedAt = DateTime.Now
            };

            await repository.Create(model);

            // Act & Assert
            await Assert.ThrowsAsync<DbUpdateConcurrencyException>(async () => await repository.Update(modelUpdated));
        }

        private static IBaseRepository<StoreModel> Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("BaseRepository.Update");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new BaseRepository<StoreModel>(context);
        }
    }
}
