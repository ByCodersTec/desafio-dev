FactoryBot.define do
  factory :transaction do
    transaction_type { 'credit' }
    value { 249.0 }
    transaction_date { '2022-05-03' }
    hour { '17:00:51' }
    card_number { '9876****1234' }
  end
end
