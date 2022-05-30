class StoreFinancialMovement < ApplicationRecord
  belongs_to :finance_report
  has_many :finance_movements, :dependent => :destroy
end
