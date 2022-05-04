class Transaction < ApplicationRecord
  before_save :set_transaction_nature_and_signal

  TRANSACTION_TYPES_DEBITS = %w[boleto financiamento aluguel].freeze

  validates :transaction_type, presence: true
  belongs_to :customer
  belongs_to :provider

  enum transaction_type: %i[debit credit ticket financing  receivable sales ted doc rent]
 

  def set_transaction_nature_and_signal
    signal = !TRANSACTION_TYPES_DEBITS.include?(self.transaction_type)
    nature = signal ? 'Entrada' : 'Saída'/
    self.signal = signal
    self.nature = nature
  end
end
