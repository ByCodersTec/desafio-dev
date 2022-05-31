class StoreFinancialMovement < ApplicationRecord
  has_many :finance_movements, :dependent => :destroy
end
