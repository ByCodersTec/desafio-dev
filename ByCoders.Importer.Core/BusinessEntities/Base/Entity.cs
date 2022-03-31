using ByCoders.Importer.Core.Interfaces;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.ComponentModel.DataAnnotations;

namespace ByCoders.Importer.Core.BusinessEntities.Base
{
    public abstract class Entity : BaseEntity, ISoftDelete
    {
        [Timestamp]
        public byte[] RowVersion { get; set; }

        public bool IsDeleted { get; set; }
    }

    public abstract class EntityConfiguration<TEntity> : BaseEntityConfiguration<TEntity> where TEntity : Entity
    {
        public override void Configure(EntityTypeBuilder<TEntity> builder)
        {
            base.Configure(builder);

            builder.Property(x => x.IsDeleted).HasDefaultValueSql("0");

            builder.HasQueryFilter(x => !x.IsDeleted);
        }
    }
}
