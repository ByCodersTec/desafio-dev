# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# This file is the source Rails uses to define your schema when running `bin/rails
# db:schema:load`. When creating a new database, `bin/rails db:schema:load` tends to
# be faster and is potentially less error prone than running all of your
# migrations from scratch. Old migrations may fail to apply correctly if those
# migrations use external dependencies or application code.
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20_220_530_054_933) do
  create_table 'finance_movements', charset: 'utf8mb4', force: :cascade do |t|
    t.integer 'type_code'
    t.datetime 'register_date'
    t.float 'value'
    t.string 'cpf'
    t.string 'card'
    t.string 'store_owner'
    t.string 'store_name'
    t.bigint 'finance_report_id', null: false
    t.datetime 'created_at', precision: 6, null: false
    t.datetime 'updated_at', precision: 6, null: false
    t.bigint 'store_financial_movement_id', null: false
    t.index ['finance_report_id'], name: 'index_finance_movements_on_finance_report_id'
    t.index ['store_financial_movement_id'], name: 'index_finance_movements_on_store_financial_movement_id'
  end

  create_table 'finance_reports', charset: 'utf8mb4', force: :cascade do |t|
    t.string 'title'
    t.datetime 'created_at', precision: 6, null: false
    t.datetime 'updated_at', precision: 6, null: false
  end

  create_table 'store_financial_movements', charset: 'utf8mb4', force: :cascade do |t|
    t.string 'store_name'
    t.float 'balance'
    t.datetime 'created_at', precision: 6, null: false
    t.datetime 'updated_at', precision: 6, null: false
  end

  add_foreign_key 'finance_movements', 'finance_reports'
  add_foreign_key 'finance_movements', 'store_financial_movements'
end
