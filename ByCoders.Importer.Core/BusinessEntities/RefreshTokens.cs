using ByCoders.Importer.Core.BusinessEntities.Base;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.ComponentModel.DataAnnotations.Schema;

namespace ByCoders.Importer.Core.BusinessEntities
{
    public class RefreshTokens : BaseEntity
    {
        public int UserID { get; set; }
        public virtual Users User { get; set; }

        [Column(TypeName = "VARCHAR(50)")]
        public string Token { get; set; }

        public DateTime Expiration { get; set; }
        public bool IsExpired => DateTime.UtcNow >= Expiration;

        public DateTime Created { get; set; }

        public DateTime? Revoked { get; set; }

        public bool IsActive => Revoked == null && !IsExpired;
    }

    public class RefreshTokensConfiguration : BaseEntityConfiguration<RefreshTokens>
    {
        public override void Configure(EntityTypeBuilder<RefreshTokens> builder)
        {
            base.Configure(builder);
        }
    }
}