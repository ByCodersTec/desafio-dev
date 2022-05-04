FactoryBot.define do
  factory :transaction do
    transaction_type { 1 }
    nature { "MyString" }
    signal { false }
  end
end
