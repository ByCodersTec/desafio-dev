require 'rails_helper'

RSpec.describe Provider, type: :model do
  context 'when create a new Provider with valid name provider and' do
    it 'create with successful' do
      expect(Provider.create(name_provider: 'Teste')).to be_valid
    end
  end

  context 'when try create a Provider but it already exists and' do
    let(:cpf) { Faker::CPF.numeric.to_s }
    let(:provider) { Provider.create(name_provider: 'Teste') }
    let(:provider2) { Provider.create(name_provider: 'Teste') }

    it 'fails' do
      expect(provider).to be_valid
      expect(provider2.save).to eq(false)
      expect { provider2.save! }.to raise_error
    end
  end
end
