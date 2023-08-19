using CNAB.Core.DomainObjects;

namespace CNAB.Financeiro.Domain.Entities
{
    public class Loja
    {
        public Guid LojaId { get; private set; }
        public string NomeLoja { get; private set; }
        public string NomeProprietario { get; private set; }
        public ICollection<Operacao> Operacao { get; set; }

        public Loja() { }

        public Loja(string nomeLoja, string nomeProprietario)
        {
            LojaId = Guid.NewGuid();
            NomeLoja = nomeLoja;
            NomeProprietario = nomeProprietario;

            Validate();
        }

        private void Validate()
        {
            Validacoes.ValidarSeNulo(NomeLoja, "O campo 'Nome Fantasia (Nome da Loja)' não pode estar nullo.");
            Validacoes.ValidarSeNulo(NomeProprietario, "O campo 'Nome Fantasia (Nome do Proprietário)' não pode estar nullo.");
        }
    }
}
