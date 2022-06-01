# frozen_string_literal: true

class FinanceMovement < ApplicationRecord
  belongs_to :finance_report
  belongs_to :store_financial_movement
  validates_presence_of :type_code, :register_date, :value, :cpf, :card, :store_owner, :store_name,
                        :store_financial_movement_id, :finance_report_id

  def transcation_type
    case type_code
    when 1
      { message: 'Débito', class: 'positive-value', signal: '+' }
    when 2
      { message: 'Boleto', class: 'negative-value', signal: '-' }
    when 3
      { message: 'Financiamento', class: 'negative-value', signal: '-' }
    when 4
      { message: 'Crédito', class: 'positive-value', signal: '+' }
    when 5
      { message: 'Recebimento Empréstimo', class: 'positive-value', signal: '+' }
    when 6
      { message: 'Vendas', class: 'positive-value', signal: '+' }
    when 7
      { message: 'Recebimento TED', class: 'positive-value', signal: '+' }
    when 8
      { message: 'Recebimento DOC', class: 'positive-value', signal: '+' }
    when 9
      { message: 'Aluguel', class: 'negative-value', signal: '-' }
    end
  end
end
