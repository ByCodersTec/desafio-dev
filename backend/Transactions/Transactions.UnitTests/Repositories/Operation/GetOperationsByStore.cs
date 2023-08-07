using Microsoft.EntityFrameworkCore;
using Transactions.Domain.Enums;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;

namespace Transactions.UnitTests.Repositories.Operation
{
    public class GetOperationsByStore
    {
        [Fact]
        public async Task GetOperationsByStore_Success()
        {
            // Arrange
            var repository = Repository();

            var model1 = new OperationModel()
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

            var model2 = new OperationModel()
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

            await repository.Create(model1);
            await repository.Create(model2);

            // Act
            var operations = await repository.GetOperationsByStore(model1.StoreId);

            // Assert
            Assert.NotNull(operations);
            Assert.Single(operations);
            Assert.Equal(model1.Id, operations[0].Id);
            Assert.Equal(100, operations[0].Value);
            Assert.Equal("000.000.000-00", operations[0].Document);
        }

        [Fact]
        public async Task GetOperationsByStore_NotFound()
        {
            // Arrange
            var repository = Repository();

            var model1 = new OperationModel()
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

            var model2 = new OperationModel()
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

            await repository.Create(model1);
            await repository.Create(model2);

            // Act
            var randomStoreId = Guid.NewGuid();

            var operations = await repository.GetOperationsByStore(randomStoreId);

            // Assert
            Assert.NotNull(operations);
            Assert.Empty(operations);
        }

        private static IOperationRepository Repository()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("OperationRepository.GetOperationsByStore");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            return new OperationRepository(context);
        }
    }
}