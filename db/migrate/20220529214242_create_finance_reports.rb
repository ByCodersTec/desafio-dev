class CreateFinanceReports < ActiveRecord::Migration[6.1]
  def change
    create_table :finance_reports do |t|
      t.string :title

      t.timestamps
    end
  end
end
