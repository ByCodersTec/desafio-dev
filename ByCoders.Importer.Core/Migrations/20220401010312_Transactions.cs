using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ByCoders.Importer.Core.Migrations
{
    public partial class Transactions : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "TransactionTypes",
                columns: table => new
                {
                    ID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Description = table.Column<string>(type: "nvarchar(100)", maxLength: 100, nullable: false),
                    RowVersion = table.Column<byte[]>(type: "rowversion", rowVersion: true, nullable: false),
                    IsDeleted = table.Column<bool>(type: "bit", nullable: false, defaultValueSql: "0")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TransactionTypes", x => x.ID);
                });

            migrationBuilder.CreateTable(
                name: "Transactions",
                columns: table => new
                {
                    ID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    TransactionTypeID = table.Column<int>(type: "int", nullable: false),
                    Date = table.Column<DateTime>(type: "datetime2", nullable: false),
                    Value = table.Column<decimal>(type: "decimal(18,2)", nullable: false),
                    CPF = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    CardNumber = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    EmporiumName = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    EmporiumOwner = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    RowVersion = table.Column<byte[]>(type: "rowversion", rowVersion: true, nullable: false),
                    IsDeleted = table.Column<bool>(type: "bit", nullable: false, defaultValueSql: "0")
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Transactions", x => x.ID);
                    table.ForeignKey(
                        name: "FK_Transactions_TransactionTypes_TransactionTypeID",
                        column: x => x.TransactionTypeID,
                        principalTable: "TransactionTypes",
                        principalColumn: "ID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.InsertData(
                table: "TransactionTypes",
                columns: new[] { "ID", "Description" },
                values: new object[,]
                {
                    { 1, "Débito" },
                    { 2, "Boleto" },
                    { 3, "Financiamento" },
                    { 4, "Crédito" },
                    { 5, "Recebimento Empréstimo" },
                    { 6, "Vendas" },
                    { 7, "Recebimento TED" },
                    { 8, "Recebimento DOC" },
                    { 9, "Aluguel" }
                });

            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "ID",
                keyValue: 1,
                column: "PasswordHash",
                value: "MqLSnQFdlCO+hdmysNBtyYWcd7p5RiH6BMU5+pqcDHGsUdG2OG7hO+JtcXW49/AU");

            migrationBuilder.CreateIndex(
                name: "IX_Transactions_TransactionTypeID",
                table: "Transactions",
                column: "TransactionTypeID");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Transactions");

            migrationBuilder.DropTable(
                name: "TransactionTypes");

            migrationBuilder.UpdateData(
                table: "Users",
                keyColumn: "ID",
                keyValue: 1,
                column: "PasswordHash",
                value: "mUiiQDxpczPetW/ElvXckLJEfRS+WG0O2WPKYeSPuObnLpYxt4uTqnOrQ24bNK+X");
        }
    }
}
