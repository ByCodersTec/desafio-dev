using ByCoders.Importer.Core.BusinessEntities.Base;
using ByCoders.Importer.Core.Identity;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.ComponentModel.DataAnnotations;

namespace ByCoders.Importer.Core.BusinessEntities
{
    public class Users : Entity
    {
        [StringLength(100)]
        public string Username { get; set; }

        [StringLength(200)]
        public string Name { get; set; }

        [StringLength(200)]
        public string PasswordHash { get; set; }


        public virtual ICollection<RefreshTokens> RefreshTokens { get; set; }
    }

    public class EmployeesConfiguration : EntityConfiguration<Users>
    {
        public override void Configure(EntityTypeBuilder<Users> builder)
        {
            base.Configure(builder);

            builder.HasData(
                new Users { ID = 1, Name = "Ricardo Zambon", Username = "ricardo.zambon", PasswordHash = PasswordHasher.HashPassword("zambon") }
            );
        }
    }
}