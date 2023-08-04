using ByCodersTec.StoreDataImporter.DocParserService;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Repository.EF.Implementation;
using ByCodersTec.StoreDataImporter.Services.Implementation;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Message;
using ByCodersTec.StoreDataImporter.ViewModel;
using Microsoft.Identity.Client;
using Moq;

namespace ByCodersTec.StoreDataImporter.TransactionService.Tests
{
    public class TransactionsServiceTests
    {
        Mock<ITransactionRepository> _transactionRepository = new Mock<ITransactionRepository>();
        Mock<ITransactionTypeRepository> _transactionTypeRepository = new Mock<ITransactionTypeRepository>();
        Mock<IStoreRepository> _storeRepository = new Mock<IStoreRepository>();
        Mock<IDocDefinitionRepository> _docDefinitionRepository = new Mock<IDocDefinitionRepository>();
        Mock<Repository.EF.IUnitOfWork> _unitOfWork = new Mock<Repository.EF.IUnitOfWork>();
        Mock<ByCodersTec.StoreDataImporter.Domain.IMessageService> _messageService = new Mock<Domain.IMessageService>();
        IDocParserService _docParserService = new DocParserService.Implementation.DocParserService();
        Mock<IUserRepository> _userRepository = new Mock<IUserRepository>();
        ITransactionService _transactionService;

        [SetUp]
        public void Setup()
        {
            _transactionService = new Services.Implementation.TransactionService(
                _userRepository.Object,
                _unitOfWork.Object,
                _messageService.Object,
                _docParserService,
                _transactionRepository.Object,
                _docDefinitionRepository.Object,
                _storeRepository.Object,
                _transactionTypeRepository.Object
            );
        }

        [Test]
        public void Should_SuccessFully_Enqueue_1_transaction_From_A_File()
        {
            AddTransactionsFromFileRequest request = new AddTransactionsFromFileRequest {
                file = GenerateStreamFromString("3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ")
            };

            _docDefinitionRepository.Setup(x => x.GetAll(x => x.Code == "CNAB", null, "Columns", null)).Returns(new List<DocDefinition> {
                GetDocDefinition()
            }.AsEnumerable());

            _messageService.Setup(x => x.Enqueue(It.IsAny<CnabImportViewModel>(), It.IsAny<Dictionary<string, object>>())).Returns(true);

            var res = _transactionService.AddTransactionsCNABFromFile(request).Result;

            Assert.AreEqual(1, res.transactions.Count);
            Assert.AreEqual("20190301", res.transactions.FirstOrDefault().Date);
        }

        [Test]
        public void Should_Successfully_Validate_1_transaction_From_A_File()
        {
            ValidateTransactionsFromFileRequest request = new ValidateTransactionsFromFileRequest
            {
                file = GenerateStreamFromString("3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO       ")
            };

            _docDefinitionRepository.Setup(x => x.GetAll(x => x.Code == "CNAB", null, "Columns", null)).Returns(new List<DocDefinition> {
                GetDocDefinition()
            }.AsEnumerable());

            _messageService.Setup(x => x.Enqueue(It.IsAny<CnabImportViewModel>(), It.IsAny<Dictionary<string, object>>())).Returns(true);

            var res = _transactionService.ValidateFile(request).Result;

            Assert.AreEqual(1, res.response.Lines.Count);
            Assert.AreEqual("20190301", res.response.Lines.FirstOrDefault().ParsedLineItem.Date);
        }

        public static Stream GenerateStreamFromString(string s)
        {
            var stream = new MemoryStream();
            var writer = new StreamWriter(stream);
            writer.Write(s);
            writer.Flush();
            stream.Position = 0;
            return stream;
        }

        public static DocDefinition GetDocDefinition()
        {
            DocDefinition defs = new DocDefinition();
            List<DocDefinitionColumn> columns = new List<DocDefinitionColumn>() {
                    new DocDefinitionColumn{
                        ClassPropName = "Type",
                        Name = "Tipo",
                        Description = "Tipo da transação",
                        End=1,
                        Start=1,
                        Lenght=1,
                        Type=DocDefinitionColumnTypeEnum.Int,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "Date",
                        Name = "Data",
                        Description = "Data da ocorrência",
                        End=9,
                        Start=2,
                        Lenght=8,
                        Type=DocDefinitionColumnTypeEnum.String,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "Val",
                        Name = "Valor",
                        Description = "Valor da movimentação. Obs. O valor encontrado no arquivo precisa ser divido por cem(valor / 100.00) para normalizá-lo.",
                        End=19,
                        Start=10,
                        Lenght=10,
                        Type=DocDefinitionColumnTypeEnum.Decimal,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "Document",
                        Name = "CPF",
                        Description = "CPF do beneficiário",
                        End=30,
                        Start=20,
                        Lenght=11,
                        Type=DocDefinitionColumnTypeEnum.String,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "Card",
                        Name = "Cartão",
                        Description = "Cartão utilizado na transação",
                        End=42,
                        Start=31,
                        Lenght=12,
                        Type=DocDefinitionColumnTypeEnum.String,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "Time",
                        Name = "Hora",
                        Description = "Hora da ocorrência atendendo ao fuso de UTC-3",
                        End=48,
                        Start=43,
                        Lenght=6,
                        Type=DocDefinitionColumnTypeEnum.String,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "Dealer",
                        Name = "Dono da loja",
                        Description = "Nome do representante da loja",
                        End=62,
                        Start=49,
                        Lenght=14,
                        Type=DocDefinitionColumnTypeEnum.String,
                        ValidationPattern = ""
                    },new DocDefinitionColumn{
                        ClassPropName = "StoreName",
                        Name = "Nome loja",
                        Description = "Nome da loja",
                        End=81,
                        Start=63,
                        Lenght=19,
                        Type=DocDefinitionColumnTypeEnum.String,
                        ValidationPattern = ""
                    }
                };
            defs = new DocDefinition
            {
                Code = "CNAB",
                Columns = columns,
                Name = "CNAB"
            };
            return defs;
        }
    }
}