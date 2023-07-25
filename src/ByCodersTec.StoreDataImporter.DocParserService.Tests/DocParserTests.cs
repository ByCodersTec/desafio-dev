using ByCodersTec.StoreDataImporter.DocParserService.Message;
using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.Services;
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
            var cnabLine = "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO � - MATRIZ";
            var request = new ParseDocLineRequest
            {
                DocLine = new ViewModel.ParseDocLineViewModel
                {
                    LineContent = cnabLine,
                    Columns = columns.ToList<IParseDocColumn>(),
                    ZeroBased = true
                }
            };
            var docline = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(request);

            Assert.AreEqual(docline.result.ParsedLineItem.Dealer, "MARIA JOSEFINA");
            Assert.AreEqual(docline.result.ParsedLineItem.StoreName, "LOJA DO � - MATRIZ");
            Assert.AreEqual(docline.result.ParsedLineItem.Type, 5);
            Assert.AreEqual(docline.result.ParsedLineItem.Date, "20190301");
            Assert.AreEqual(docline.result.ParsedLineItem.Val, "0000013200");
            Assert.AreEqual(docline.result.ParsedLineItem.Card, "3123****7687");
            Assert.AreEqual(docline.result.ParsedLineItem.Time, "145607");
            Assert.AreEqual(docline.result.ParsedLineItem.Document, "55641815063");
        }

        [Test]
        public void Should_Parse_Multiple_CNAB_Document_Lines()
        {
            var columns = GetCNABColumns();
            var cnabLine = new string[] {
                "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO � - MATRIZ",
                "3201903010000014200096206760174753****3153153453JO�O MACEDO   BAR DO JO�O"
            };
            var request = new ParseDocRequest
            {
                DocLines = new List<ViewModel.ParseDocLineViewModel> {
                    new ViewModel.ParseDocLineViewModel
                {
                    LineContent = cnabLine[0],
                    Columns = columns.ToList<IParseDocColumn>(),
                    ZeroBased = true
                },
                    new ViewModel.ParseDocLineViewModel
                {
                    LineContent = cnabLine[1],
                    Columns = columns.ToList<IParseDocColumn>(),
                    ZeroBased = true
                }
                }
            };
            var docline = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(request);

            Assert.AreEqual(2, docline.result.Lines.Count);
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Dealer, "MARIA JOSEFINA");
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.StoreName, "LOJA DO � - MATRIZ");
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Type, 5);
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Date, "20190301");
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Val, "0000013200");
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Card, "3123****7687");
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Time, "145607");
            Assert.AreEqual(docline.result.Lines[0].ParsedLineItem.Document, "55641815063");
        }

        [Test]
        public void Should_Parse_A_CNAB_Document_Line_And_Successfully_Validate_A_Date_Field()
        {
            var columns = GetCNABColumns();
            var cnabLine = "5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO � - MATRIZ";
            var request = new ParseDocLineRequest
            {
                DocLine = new ViewModel.ParseDocLineViewModel
                {
                    LineContent = cnabLine,
                    Columns = columns.ToList<IParseDocColumn>(),
                    ZeroBased = true
                }
            };
            var docline = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(request);

            Assert.AreEqual(docline.result.ParsedLineItem.Dealer, "MARIA JOSEFINA");
            Assert.AreEqual(docline.result.ParsedLineItem.StoreName, "LOJA DO � - MATRIZ");
            Assert.AreEqual(docline.result.ParsedLineItem.Type, 5);
            Assert.AreEqual(docline.result.ParsedLineItem.Date, "20190301");
            Assert.AreEqual(docline.result.ParsedLineItem.Val, "0000013200");
            Assert.AreEqual(docline.result.ParsedLineItem.Card, "3123****7687");
            Assert.AreEqual(docline.result.ParsedLineItem.Time, "145607");
            Assert.AreEqual(docline.result.ParsedLineItem.Document, "55641815063");
        }

        [Test]
        public void Should_Parse_A_CNAB_Document_Line_AndThrow_An_Error_Validating_A_Date_Field()
        {
            var columns = GetCNABColumns();
            var cnabLine = "5211903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO � - MATRIZ";
            var request = new ParseDocLineRequest
            {
                DocLine = new ViewModel.ParseDocLineViewModel
                {
                    LineContent = cnabLine,
                    Columns = columns.ToList<IParseDocColumn>(),
                    ZeroBased = true
                }
            };
            var response = _docParserService.ParseFileLinseFromString<CnabImportViewModel>(request);

            Assert.AreEqual(false, response.result.IsValid);
            Assert.AreEqual("21190301", response.result.ParsedLineItem.Date);
        }

        private List<DocColumnViewModel> GetCNABColumns()
        {
            return new List<DocColumnViewModel>() {
                    new DocColumnViewModel{
                        ClassPropName = "Type",
                        Name = "Tipo",
                        Description = "Tipo da transa��o",
                        End=1,
                        Start=1,
                        Length=1,
                        Type=DocDefinitionColumnTypeEnumViewModel.Int
                    },new DocColumnViewModel{
                        ClassPropName = "Date",
                        Name = "Data",
                        Description = "Data da ocorr�ncia",
                        End=9,
                        Start=2,
                        Length=8,
                        Type=DocDefinitionColumnTypeEnumViewModel.String,
                        ValidationPattern=@"(19|20)\d{2}(0[1-9]|1[1,2])(0[1-9]|[12][0-9]|3[01])"
                    },new DocColumnViewModel{
                        ClassPropName = "Val",
                        Name = "Valor",
                        Description = "Valor da movimenta��o. Obs. O valor encontrado no arquivo precisa ser divido por cem(valor / 100.00) para normaliz�-lo.",
                        End=19,
                        Start=10,
                        Length=10,
                        Type=DocDefinitionColumnTypeEnumViewModel.Decimal
                    },new DocColumnViewModel{
                        ClassPropName = "Document",
                        Name = "CPF",
                        Description = "CPF do benefici�rio",
                        End=30,
                        Start=20,
                        Length=11,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Card",
                        Name = "Cart�o",
                        Description = "Cart�o utilizado na transa��o",
                        End=42,
                        Start=31,
                        Length=12,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Time",
                        Name = "Hora",
                        Description = "Hora da ocorr�ncia atendendo ao fuso de UTC-3",
                        End=48,
                        Start=43,
                        Length=6,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "Dealer",
                        Name = "Dono da loja",
                        Description = "Nome do representante da loja",
                        End=62,
                        Start=49,
                        Length=14,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    },new DocColumnViewModel{
                        ClassPropName = "StoreName",
                        Name = "Nome loja",
                        Description = "Nome da loja",
                        End=81,
                        Start=63,
                        Length=19,
                        Type=DocDefinitionColumnTypeEnumViewModel.String
                    }
                };
        }
    }
}