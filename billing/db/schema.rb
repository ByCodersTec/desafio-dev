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

ActiveRecord::Schema[7.0].define(version: 2022_05_04_210141) do
  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "customers", force: :cascade do |t|
    t.string "cpf"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "providers", force: :cascade do |t|
    t.string "name_owner"
    t.string "name_provider"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "transactions", force: :cascade do |t|
    t.bigint "customers_id"
    t.bigint "providers_id"
    t.integer "transaction_type"
    t.string "nature"
    t.boolean "signal"
    t.float "value"
    t.date "transaction_date"
    t.string "hour"
    t.string "card_number"
    t.bigint "customer_id"
    t.bigint "provider_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["customers_id"], name: "index_transactions_on_customers_id"
    t.index ["providers_id"], name: "index_transactions_on_providers_id"
  end

  add_foreign_key "transactions", "customers", column: "customers_id"
  add_foreign_key "transactions", "providers", column: "providers_id"
end
