using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ByCodersTec.StoreDataImporter.Repository.EF.Migrations
{
    /// <inheritdoc />
    public partial class ColumnValidationPattern : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "ValidationPattern",
                table: "DocDefinitionColumns",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "ValidationPattern",
                table: "DocDefinitionColumns");
        }
    }
}
