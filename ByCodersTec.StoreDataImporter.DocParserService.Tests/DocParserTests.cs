using ByCodersTec.StoreDataImporter.ViewModel;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

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

            //var formatedDate = Regex.Replace(docline.Date+docline.Time, @"(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})", "$1-$2-$3 $4:$5:$6");
            //var date = DateTime.Parse(formatedDate);

            Assert.AreEqual(docline.Dealer, "MARIA JOSEFINA");
            Assert.AreEqual(docline.StoreName, "LOJA DO Ó - MATRIZ");
            Assert.AreEqual(docline.Type, 5);
            Assert.AreEqual(docline.Date, "20190301");
            Assert.AreEqual(docline.Val, "0000013200");
            Assert.AreEqual(docline.Card, "3123****7687");
            Assert.AreEqual(docline.Time, "145607");
            Assert.AreEqual(docline.Document, "55641815063");
        }

        [Test]
        public void Should_Parse_2_CNAB_Document_Lines()
        {
            var columns = GetCNABColumns();
            var cnabLine = new string[] {
                "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ",
                "3201903010000014200096206760174753****3153153453JOÃO MACEDO   BAR DO JOÃO"
            };

            var docline = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(cnabLine.ToList(), columns, zeroBased: true);

            //var formatedDate = Regex.Replace(docline.Date + docline.Time, @"(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})", "$1-$2-$3 $4:$5:$6");
            //var date = DateTime.Parse(formatedDate);

            Assert.AreEqual(2, docline.Count);
            Assert.AreEqual(docline[0].Dealer, "MARIA JOSEFINA");
            Assert.AreEqual(docline[0].StoreName, "LOJA DO Ó - MATRIZ");
            Assert.AreEqual(docline[0].Type, 5);
            Assert.AreEqual(docline[0].Date, "20190301");
            Assert.AreEqual(docline[0].Val, "0000013200");
            Assert.AreEqual(docline[0].Card, "3123****7687");
            Assert.AreEqual(docline[0].Time, "145607");
            Assert.AreEqual(docline[0].Document, "55641815063");
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