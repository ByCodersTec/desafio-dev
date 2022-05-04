FactoryBot.define do
  factory :customer do
    cpf { Faker::CPF.numeric.to_s }

    trait :invalid do
      cpf { '123456' }
    end
  end
end
