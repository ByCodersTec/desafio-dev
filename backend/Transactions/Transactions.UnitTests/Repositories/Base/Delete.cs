using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Base
{
    public class Delete
    {
        [Fact]
        public async Task Delete_Success()
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

            // Act
            await repository.Create(model);

            var storesBeforeDelete = await repository.Search();

            await repository.Delete(model.Id);

            var storesAfterDelete = await repository.Search();

            // Assert
            Assert.Single(storesBeforeDelete);
            Assert.Empty(storesAfterDelete);
        }

        [Fact]
        public async Task Delete_WithModelNotFound()
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

            // Act
            await repository.Create(model);

            var storesBeforeDelete = await repository.Search();

            // Act & Assert
            Assert.Single(storesBeforeDelete);

            await Assert.ThrowsAsync<DbUpdateConcurrencyException>(async () => await repository.Delete(notFoundId));
        }

        private static IBaseRepository<StoreModel> Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("BaseRepository.Delete");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new BaseRepository<StoreModel>(context);
        }
    }
}
