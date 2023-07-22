using AutoFixture;
using ByCodersTec.StoreDataImporter.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF
{
    public static class Seeder
    {
        public static void Seed(this AppContext salesContext)
        {
            if (!salesContext.Users.Any())
            {
                //--- The next two lines add 100 rows to your database
                List<User> users = new List<User> { 
                    new User { 
                        Id = Guid.NewGuid(), 
                        Name = "Charles dos Santos França",
                        Email = "charles-franca@live.com",
                        Username = "charlesfranca89",
                        createdAt = DateTime.Now,
                        updatedAt = DateTime.Now,
                        Password = "123123"
                    }
                };
                salesContext.AddRange(users);
            }

            if (!salesContext.DocDefinitions.Any())
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
                        Type=DocDefinitionColumnTypeEnum.Int
                    },new DocDefinitionColumn{
                        ClassPropName = "Date",
                        Name = "Data",
                        Description = "Data da ocorrência",
                        End=9,
                        Start=2,
                        Lenght=8,
                        Type=DocDefinitionColumnTypeEnum.String
                    },new DocDefinitionColumn{
                        ClassPropName = "Val",
                        Name = "Valor",
                        Description = "Valor da movimentação. Obs. O valor encontrado no arquivo precisa ser divido por cem(valor / 100.00) para normalizá-lo.",
                        End=19,
                        Start=10,
                        Lenght=10,
                        Type=DocDefinitionColumnTypeEnum.Decimal
                    },new DocDefinitionColumn{
                        ClassPropName = "Document",
                        Name = "CPF",
                        Description = "CPF do beneficiário",
                        End=30,
                        Start=20,
                        Lenght=11,
                        Type=DocDefinitionColumnTypeEnum.String
                    },new DocDefinitionColumn{
                        ClassPropName = "Card",
                        Name = "Cartão",
                        Description = "Cartão utilizado na transação",
                        End=42,
                        Start=31,
                        Lenght=12,
                        Type=DocDefinitionColumnTypeEnum.String
                    },new DocDefinitionColumn{
                        ClassPropName = "Time",
                        Name = "Hora",
                        Description = "Hora da ocorrência atendendo ao fuso de UTC-3",
                        End=48,
                        Start=43,
                        Lenght=6,
                        Type=DocDefinitionColumnTypeEnum.String
                    },new DocDefinitionColumn{
                        ClassPropName = "Dealer",
                        Name = "Dono da loja",
                        Description = "Nome do representante da loja",
                        End=62,
                        Start=49,
                        Lenght=14,
                        Type=DocDefinitionColumnTypeEnum.String
                    },new DocDefinitionColumn{
                        ClassPropName = "StoreName",
                        Name = "Nome loja",
                        Description = "Nome da loja",
                        End=81,
                        Start=63,
                        Lenght=19,
                        Type=DocDefinitionColumnTypeEnum.String
                    }
                };
                defs = new DocDefinition
                {
                    Code = "CNAB",
                    Columns = columns,
                    Name = "CNAB"
                };
                salesContext.DocDefinitions.Add(defs);
            }

            salesContext.SaveChanges();
        }
    }
}
