# frozen_string_literal: true

class CreateFinanceMovements < ActiveRecord::Migration[6.1]
  def change
    create_table :finance_movements do |t|
      t.integer :type_code
      t.datetime :register_date
      t.float :value
      t.string :cpf
      t.string :card
      t.string :store_owner
      t.string :store_name
      t.references :finance_report, null: false, foreign_key: true

      t.timestamps
    end
  end
end
