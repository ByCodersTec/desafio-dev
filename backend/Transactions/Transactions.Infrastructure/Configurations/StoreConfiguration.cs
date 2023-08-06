using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Transactions.Domain.Models;

namespace Transactions.Infraestructure.Configurations
{
    public class StoreConfiguration : IEntityTypeConfiguration<StoreModel>
    {
        public void Configure(EntityTypeBuilder<StoreModel> builder)
        {
            builder
                .HasKey(t => t.Id);

            builder
                .Property(t => t.Id)
                .HasColumnName("id");

            builder
                .Property(t => t.Name)
                .HasColumnName("name");

            builder
                .Property(t => t.OwnerName)
                .HasColumnName("owner_name");

            builder
                .Property(t => t.CreatedAt)
                .HasColumnName("created_at");

            builder
                .Property(t => t.UpdatedAt)
                .HasColumnName("updated_at");

            builder
                .ToTable("stores", schema: "transactions");
        }
    }
}
