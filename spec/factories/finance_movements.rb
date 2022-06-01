# frozen_string_literal: true

FactoryBot.define do
  factory :finance_movement do
    type_code { 1 }
    register_date { DateTime.now }
    value { 190.00 }
    cpf { '45437845934' }
    card { '4753****3153' }
    store_owner { 'CASSIO' }
    store_name { 'LOJA DO CASSIO' }
    store_financial_movement_id {}
    finance_report_id {}
  end
end
