require 'rails_helper'

RSpec.describe Transaction, type: :model do
  
  let!(:customer) {Customer.create(cpf: Faker::CPF.numeric.to_s) }
  let!(:provider) { Provider.create(name_provider: 'Teste') }

  context 'when create a new Transaction with valid data and' do
    let(:transaction) do
      Transaction.create(
        provider_id: provider.id,
        customer_id: customer.id,
        transaction_type: 'credit',
        value: 192.0,
        transaction_date: '2022-03-01',
        hour: '17:27:12',
        card_number: '6777****1313'
      )
    end

    it 'create with successful' do
      expect(transaction).to be_valid
      expect(transaction.provider_id).to eq(provider.id)
      expect(transaction.customer_id).to eq(customer.id)
      expect(transaction.transaction_type).to eq('credit')
      expect(transaction.value).to eq(192.0)
      expect(transaction.transaction_date.class).to eq(Date)
      expect(transaction.hour).to eq('17:27:12')
      expect(transaction.card_number).to eq('6777****1313')
    end
  end

  context 'when try create a Transaction without Provider and' do
    let(:transaction) do
      Transaction.create(
        customer_id: customer.id,
        transaction_type: 'credit',
        value: 192.0,
        transaction_date: '2022-03-01',
        hour: '17:27:12',
        card_number: '6777****1313'
      )
    end

    it 'fails' do
      expect(transaction).to be_invalid
      expect { transaction.save! }.to raise_error(StandardError)
    end
  end

  context 'when try create a Transaction without Customer' do
    let(:transaction) do
      Transaction.create(
        provider_id: provider.id,
        transaction_type: :credit,
        value: 192.0,
        transaction_date: '2022-03-01',
        hour: '17:27:12',
        card_number: '6777****1313'
      )
    end

    it 'when exist fails in transaction' do
      expect(transaction).to be_invalid
      expect { transaction.save! }.to raise_error(StandardError)
    end
  end
end
