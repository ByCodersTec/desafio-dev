using CNAB.Core.DomainObjects;

namespace CNAB.Financeiro.Domain.Entities
{
    public class TipoTransacao
    {
        public int Tipo { get; private set; }
        public string Descricao { get; private set; }
        public string Natureza { get; private set; }
        public string Sinal { get; private set; }
        public ICollection<Operacao> Operacao { get; set; }
        public TipoTransacao() { }

        public TipoTransacao(int tipo, string descricao, string natureza, string sinal)
        {
            Tipo = tipo;
            Descricao = descricao;
            Natureza = natureza;
            Sinal = sinal;

            Validation();
        }

        private void Validation()
        {
            Validacoes.ValidarSeVazio(Descricao, "O campo 'Descricao' não pode estar vazio.");
            Validacoes.ValidarSeVazio(Natureza, "O campo 'Natureza' não pode estar vazio.");
            Validacoes.ValidarSeVazio(Sinal, "O campo 'Sinal' não pode estar vazio.");
        }
    }
}
