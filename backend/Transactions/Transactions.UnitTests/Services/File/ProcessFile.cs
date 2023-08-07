using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Reflection;
using Transactions.Application.AutoMapper;
using Transactions.Domain.Interfaces;
using Transactions.Domain.Models;
using Transactions.Infraestructure.Context;
using Transactions.Infrastructure.Repositories;
using Transactions.Services.Interfaces;
using Transactions.Services.Services;

namespace Transactions.UnitTests.Services.File
{
    public class ProcessFile
    {
        private static IStoreRepository _storeRepository;
        private static IOperationRepository _operationRepository;

        [Fact]
        public async Task ProcessFile_Success()
        {
            // Arrange
            var service = Service();

            string fileName = "CNAB-Test.txt";
            string filePath = Path.Combine("Services/File", fileName);

            FileStream fileStream = new FileStream(filePath, FileMode.Open, FileAccess.Read);

            FileStreamResult fileStreamResult = new FileStreamResult(fileStream, "application/octet-stream")
            {
                FileDownloadName = fileName
            };

            IFormFile file = new FormFile(fileStreamResult.FileStream, 0, fileStreamResult.FileStream.Length, "name",  fileStreamResult.FileDownloadName);

            // Act
            await service.ProcessFile(file);

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
        public async Task ProcessFile_CheckStoreBalance()
        {
            // Arrange
            var service = Service();

            string fileName = "CNAB-Test.txt";
            string filePath = Path.Combine("Services/File", fileName);

            FileStream fileStream = new FileStream(filePath, FileMode.Open, FileAccess.Read);

            FileStreamResult fileStreamResult = new FileStreamResult(fileStream, "application/octet-stream")
            {
                FileDownloadName = fileName
            };

            IFormFile file = new FormFile(fileStreamResult.FileStream, 0, fileStreamResult.FileStream.Length, "name", fileStreamResult.FileDownloadName);

            // Act
            await service.ProcessFile(file);

            var store1 = await _storeRepository.GetStoreByName("BAR DO JOÃO");
            var store2 = await _storeRepository.GetStoreByName("LOJA DO Ó - MATRIZ");

            // Assert
            Assert.NotNull(store1);
            Assert.Equal(-25400, store1.Balance);

            Assert.NotNull(store2);
            Assert.Equal(13200, store2.Balance);
        }

        private static IFileService Service()
        {
            DbContextOptions<ApplicationDbContext> options;
            var builder = new DbContextOptionsBuilder<ApplicationDbContext>();
            builder.UseInMemoryDatabase("FileService.ProcessFile");
            options = builder.Options;
            var context = new ApplicationDbContext(options);

            context.Database.EnsureDeleted();
            context.Database.EnsureCreated();

            _storeRepository = new StoreRepository(context);
            _operationRepository = new OperationRepository(context);

            var mapper = new Mapper(AutoMapperSetup.RegisterMapping());

            var accountService = new AccountService(_storeRepository, _operationRepository, mapper);

            return new FileService(accountService);
        }
    }
}
