class Customer < ApplicationRecord
  validate :cpf_valid, on: %i[create update]
  validates :cpf, presence: true, uniqueness: true

  def cpf_valid
    return errors.add(:base, "CPF #{self.cpf} invalid") unless CPF.valid?(self.cpf)
  end

  private

  def record_exists?
    return false unless Customer.find_by_cpf(self.cpf).present?

    true
  end
end
