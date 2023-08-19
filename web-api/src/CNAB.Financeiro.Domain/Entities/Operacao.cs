using CNAB.Core.DomainObjects;

namespace CNAB.Financeiro.Domain.Entities
{
    public class Operacao : IAggregateRoot
    {
        public Guid OperacaoId { get; private set; }
        public int TipoTransacaoId { get; private set; }
        public Guid LojaId { get; private set; }
        public DateTime DataOcorrencia { get; private set; }
        public decimal Valor { get; private set; }
        public string Cpf { get; private set; }
        public string CartaoTransacao { get; private set; }
        public string HoraOcorrencia { get; private set; }
        public TipoTransacao TipoTransacao { get; set; }
        public Loja Loja { get; set; }
        public Operacao() { }

        public Operacao(int tipoTransacaoId, Guid lojaId, DateTime dataOcorrencia, decimal valor, string cpf,
            string cartaoTransacao, string horaOcorrencia)
        {
            OperacaoId = Guid.NewGuid();
            LojaId = lojaId;
            TipoTransacaoId = tipoTransacaoId;
            DataOcorrencia = dataOcorrencia;
            Valor = valor;
            Cpf = cpf;
            CartaoTransacao = cartaoTransacao;
            HoraOcorrencia = horaOcorrencia;

            Validation();
        }

        public void SetId()
        {
            OperacaoId = Guid.NewGuid();
        }

        // Apenas como exemplo de validação.
        private void Validation()
        {
            Validacoes.ValidarSeVazio(Cpf, "O campo 'Cpf' não pode estar vazio.");
            Validacoes.ValidarSeNulo(TipoTransacaoId, "O campo 'Tipo de Transação' não pode estar nullo.");
            Validacoes.ValidarSeNulo(LojaId, "O campo 'Id da Loja' não pode estar nullo.");
            Validacoes.ValidarSeNulo(DataOcorrencia, "O campo 'Data da Ocorrência' não pode estar nullo.");
            Validacoes.ValidarSeNulo(Valor, "O campo 'Data da Ocorrência' não pode estar nullo.");
            Validacoes.ValidarSeNulo(CartaoTransacao, "O campo 'Cartão da Transacao' não pode estar nullo.");
            Validacoes.ValidarSeNulo(HoraOcorrencia, "O campo 'Hora da Ocorrência' não pode estar nullo.");
        }
    }
}
