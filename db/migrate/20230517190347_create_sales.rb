class CreateSales < ActiveRecord::Migration[7.0]
  def change
    create_table :sales do |t|
      t.integer :type
      t.string :occ_day
      t.bigint :value
      t.bigint :cpf
      t.string :card_number
      t.string :occ_hour
      t.string :store_owner
      t.string :store_name

      t.timestamps
    end
  end
end
