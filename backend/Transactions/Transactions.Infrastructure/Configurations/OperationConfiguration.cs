using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Transactions.Domain.Models;

namespace Transactions.Infraestructure.Configurations
{
    public class OperationConfiguration : IEntityTypeConfiguration<OperationModel>
    {
        public void Configure(EntityTypeBuilder<OperationModel> builder)
        {
            builder
                .HasKey(t => t.Id);

            builder
                .Property(t => t.Id)
                .HasColumnName("id");

            builder
                .Property(t => t.TransactionType)
                .HasColumnName("transaction_type");

            builder
                .Property(t => t.Date)
                .HasColumnName("date");

            builder
                .Property(t => t.Value)
                .HasColumnName("value");

            builder
                .Property(t => t.Document)
                .HasColumnName("document");

            builder
                .Property(t => t.Card)
                .HasColumnName("card");

            builder
                .Property(t => t.CreatedAt)
                .HasColumnName("created_at");

            builder
                .Property(t => t.UpdatedAt)
                .HasColumnName("updated_at");

            builder
                .ToTable("operations", schema: "transactions");
        }
    }
}
