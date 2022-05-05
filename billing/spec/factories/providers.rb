FactoryBot.define do
  factory :provider do
    name_owner { Faker::Name.name }
    name_provider { Faker::Name.first_name }

    trait :invalid do
      name_provider { nil }
    end
  end
end
