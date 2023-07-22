using ByCodersTec.StoreDataImporter.ViewModel;
using NUnit.Framework;
using System.Collections.Generic;

namespace ByCodersTec.StoreDataImporter.DocParserService.Tests
{
    public class DocParserTests
    {
        IDocParserService _docParserService;
        [SetUp]
        public void Setup()
        {
            _docParserService = StructureMapContainer.Instance.container.GetInstance<IDocParserService>();
        }

        [Test]
        public void Should_Parse_A_CNAB_Document_Line()
        {
            var columns = GetCNABColumns();
            var cnabLine = "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ";

            var docline = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(cnabLine, columns, zeroBased: true);

            Assert.AreEqual(docline.Dealer, "MARIA JOSEFINA");
            Assert.AreEqual(docline.StoreName, "LOJA DO Ó - MATRIZ");
            Assert.AreEqual(docline.Type, 5);
            Assert.AreEqual(docline.Date, "20190301");
            Assert.AreEqual(docline.Val, "0000013200");
            Assert.AreEqual(docline.Card, "3123****7687");
            Assert.AreEqual(docline.Time, "145607");
            Assert.AreEqual(docline.Document, "55641815063");
        }

        private List<DocColumnViewModel> GetCNABColumns() {
            return new List<DocColumnViewModel>() {
                    new DocColumnViewModel{
                        ClassPropName = "Type",
                        Name = "Tipo",
                        Description = "Tipo da transação",
                        End=1,
                        Start=1,
                        Lenght=1,
                        Type=DocDefinitionColumnTypeEnumViewModel.Int
                    },new DocColumnViewModel{
                        ClassPropName = "Date",
                        Name = "Data",
                        Description = "Data da ocorrência",
                        End=9,
                        Start=2,
                        Lenght=8,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Val",
                        Name = "Valor",
                        Description = "Valor da movimentação. Obs. O valor encontrado no arquivo precisa ser divido por cem(valor / 100.00) para normalizá-lo.",
                        End=19,
                        Start=10,
                        Lenght=10,
                        Type=DocDefinitionColumnTypeEnumViewModel.Decimal
                    },new DocColumnViewModel{
                        ClassPropName = "Document",
                        Name = "CPF",
                        Description = "CPF do beneficiário",
                        End=30,
                        Start=20,
                        Lenght=11,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Card",
                        Name = "Cartão",
                        Description = "Cartão utilizado na transação",
                        End=42,
                        Start=31,
                        Lenght=12,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Time",
                        Name = "Hora",
                        Description = "Hora da ocorrência atendendo ao fuso de UTC-3",
                        End=48,
                        Start=43,
                        Lenght=6,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Dealer",
                        Name = "Dono da loja",
                        Description = "Nome do representante da loja",
                        End=62,
                        Start=49,
                        Lenght=14,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "StoreName",
                        Name = "Nome loja",
                        Description = "Nome da loja",
                        End=81,
                        Start=63,
                        Lenght=19,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    }
                };
        }
    }
}