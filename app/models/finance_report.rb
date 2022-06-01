class FinanceReport < ApplicationRecord
  has_many :finance_movements, dependent: :destroy
end
