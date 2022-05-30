class FinanceMovement < ApplicationRecord
  belongs_to :finance_report

  def transcation_type
    case type_code
    when 1
      "Débito"
    when 2
      "Boleto"
    when 3
      "Financiamento"
    when 4
      "Crédito"
    when 5
      "Recebimento Empréstimo"
    when 6
      "Vendas"
    when 7
      "Recebimento TED"
    when 8
      "Recebimento DOC"
    when 9
      "Aluguel"
    end
  end
end
