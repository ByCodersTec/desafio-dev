using ByCoders.Importer.Core.BusinessEntities.Base;
using Microsoft.EntityFrameworkCore.Metadata.Builders;

namespace ByCoders.Importer.Core.BusinessEntities
{
    public class Transactions : Entity
    {
        public int TransactionTypeID { get; set; }
        public virtual TransactionTypes TransactionType { get; set; }

        public DateTime Date { get; set; }

        public decimal Value { get; set; }

        public string CPF { get; set; }

        public string CardNumber { get; set; }

        public string EmporiumName { get; set; }

        public string EmporiumOwner { get; set; }
    }

    public class TransactionsConfiguration : EntityConfiguration<Transactions>
    {
        public override void Configure(EntityTypeBuilder<Transactions> builder)
        {
            base.Configure(builder);
        }
    }
}