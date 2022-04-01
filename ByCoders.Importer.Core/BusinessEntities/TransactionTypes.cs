using ByCoders.Importer.Core.BusinessEntities.Base;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.ComponentModel.DataAnnotations;

namespace ByCoders.Importer.Core.BusinessEntities
{
    public class TransactionTypes : Entity
    {
        [StringLength(100)]
        public string Description { get; set; }
    }

    public class TransactionTypesConfiguration : EntityConfiguration<TransactionTypes>
    {
        public override void Configure(EntityTypeBuilder<TransactionTypes> builder)
        {
            base.Configure(builder);

            builder.HasData(new[] {
                new TransactionTypes { ID = 1, Description = "Débito" },
                new TransactionTypes { ID = 2, Description = "Boleto" },
                new TransactionTypes { ID = 3, Description = "Financiamento" },
                new TransactionTypes { ID = 4, Description = "Crédito" },
                new TransactionTypes { ID = 5, Description = "Recebimento Empréstimo" },
                new TransactionTypes { ID = 6, Description = "Vendas" },
                new TransactionTypes { ID = 7, Description = "Recebimento TED" },
                new TransactionTypes { ID = 8, Description = "Recebimento DOC" },
                new TransactionTypes { ID = 9, Description = "Aluguel" }
            });
        }
    }
}