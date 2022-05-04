class CreateProviders < ActiveRecord::Migration[7.0]
  def change
    create_table :providers do |t|
      t.string :name_owner
      t.string :name_provider

      t.timestamps
    end
  end
end
