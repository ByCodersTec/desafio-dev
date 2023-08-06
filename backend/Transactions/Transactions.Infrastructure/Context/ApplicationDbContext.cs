using Microsoft.EntityFrameworkCore;
using System.Reflection;
using Transactions.Domain.Models;

namespace Transactions.Infraestructure.Context
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }

        public DbSet<StoreModel> Store => Set<StoreModel>();
        public DbSet<OperationModel> Operation => Set<OperationModel>();

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.ApplyConfigurationsFromAssembly(Assembly.GetExecutingAssembly());
        }
    }
}
