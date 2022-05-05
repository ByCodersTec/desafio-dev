require 'rails_helper'

RSpec.describe Customer, type: :model do
  context 'when create a new Customer with valid CPF' do
    subject(:customer) { build :customer }

    describe 'validation' do
      it { should be_valid }
    end
  end

  context 'when create a new Customer with invalid CPF' do
    subject(:customer) { build :customer , :invalid }
    describe 'validation' do
      it { should_not be_valid }
    end
  end

  context 'when try create a Customer but it already exists and' do
    let(:cpf) { Faker::CPF.numeric.to_s }
    subject(:customer) { create :customer, cpf: cpf }
    subject(:customer2) { build :customer, cpf: cpf }

    it 'fails' do  
      expect(customer).to be_valid
      expect(customer2.save).to eq(false)
      expect { customer2.save! }.to raise_error
    end
  end

  context 'when try update a Customer with a invalid CPF and' do
    subject(:customer) { create :customer }

    it 'fails' do
      expect(customer.update(cpf: '123456789')).to eq(false)
      expect { customer.update!(cpf: '123456789') }.to raise_error
    end
  end
end
