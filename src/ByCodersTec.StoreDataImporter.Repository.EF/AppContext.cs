using ByCodersTec.StoreDataImporter.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Repository.EF
{
    public class AppContext : DbContext, IAppContext
    {
        public AppContext()
            : base()
        {}

        public DbSet<User> Users { get; set; }
        public DbSet<DocDefinition> DocDefinitions { get; set; }
        public DbSet<DocDefinitionColumn> DocDefinitionColumns { get; set; }
        public DbSet<Transaction> Transactions { get; set; }
        public DbSet<TransactionType> TransactionTypes { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            //optionsBuilder.UseSqlServer(AppConfiguration.GetConnectionString());
            var host = Environment.GetEnvironmentVariable("DB_HOST");
            var db = Environment.GetEnvironmentVariable("DB_NAME");
            var password = Environment.GetEnvironmentVariable("DB_SA_PASSWORD");

            ////var host = "localhost,8002";
            //var db = "StoreImporter";
            //var password = "S3cur3P@ssW0rd!";
            //; TrustServerCertificate = True;
            var connectionString = $"Data Source={host};Initial Catalog={db};User ID=sa;Password={password}; TrustServerCertificate = True;";
            optionsBuilder.UseSqlServer(connectionString);
        }


    }

}
