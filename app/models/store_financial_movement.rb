# frozen_string_literal: true

class StoreFinancialMovement < ApplicationRecord
  has_many :finance_movements, dependent: :destroy
end
