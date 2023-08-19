using System.ComponentModel;

namespace CNAB.Financeiro.Domain.Enum
{
    public enum TipoOperacaoDescricaoEnum
    {
        [Description("Débito")]
        [DefaultValue("Débito")]
        Debito,

        [Description("Boleto")]
        Boleto,

        [Description("Financiamento")]
        Financiamento,

        [Description("Crédito")]
        Credito,

        [Description("RecebimentoEmprestimo")]
        RecebimentoEmprestimo,

        [Description("Vendas")]
        Vendas,

        [Description("RecebimentoTED")]
        RecebimentoTED,

        [Description("RecebimentoDOC")]
        RecebimentoDOC,

        [Description("Aluguel")]
        Aluguel
    }
}
