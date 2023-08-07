using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Base
{
    public class CreateMany
    {
        [Fact]
        public async Task CreateMany_Success()
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

            // Act
            await repository.CreateMany(new List<StoreModel>() { model1, model2 });

            var stores = await repository.Search();

            // Assert
            Assert.Equal(2, stores.Count);
        }

        private static IBaseRepository<StoreModel> Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("BaseRepository.CreateMany");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new BaseRepository<StoreModel>(context);
        }
    }
}
