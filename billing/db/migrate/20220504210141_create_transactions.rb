class CreateTransactions < ActiveRecord::Migration[7.0]
  def change
    create_table :transactions do |t|
      t.references :customers, foreign_key: true
      t.references :providers, foreign_key: true

      t.integer :transaction_type
      t.string :nature
      t.boolean :signal
      t.float :value
      t.date :transaction_date
      t.string :hour
      t.string :card_number
      t.bigint :customer_id
      t.bigint :provider_id
          
      t.timestamps
    end
  end
end
