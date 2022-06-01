# frozen_string_literal: true

class CreateStoreFinancialMovements < ActiveRecord::Migration[6.1]
  def change
    create_table :store_financial_movements do |t|
      t.string :store_name
      t.float :balance

      t.timestamps
    end
  end
end
