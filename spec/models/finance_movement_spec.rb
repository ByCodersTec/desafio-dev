require 'rails_helper'

RSpec.describe FinanceMovement, type: :model do
  describe 'Associations' do
    it { is_expected.to belong_to(:finance_report) }
    it { is_expected.to belong_to(:store_financial_movement) }
  end

  describe 'Validations' do
    it { is_expected.to validate_presence_of(:type_code) }
    it { is_expected.to validate_presence_of(:register_date) }
    it { is_expected.to validate_presence_of(:value) }
    it { is_expected.to validate_presence_of(:cpf) }
    it { is_expected.to validate_presence_of(:card) }
    it { is_expected.to validate_presence_of(:store_owner) }
    it { is_expected.to validate_presence_of(:store_name) }
    it { is_expected.to validate_presence_of(:store_financial_movement_id) }
    it { is_expected.to validate_presence_of(:finance_report_id) }
  end

  describe 'Teste método de transaction code' do
    before(:each) do
      @finance_report = create(:finance_report)
      @store_financial = create(:store_financial_movement)
    end
    it 'deve retornar débito com type_code = 1' do
      finance_movement = create(:finance_movement, type_code: 1, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Débito', class: 'positive-value', signal: '+' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar boleto com type_code = 2' do
      finance_movement = create(:finance_movement, type_code: 2, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Boleto', class: 'negative-value', signal: '-' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar financiamento com type_code = 3' do
      finance_movement = create(:finance_movement, type_code: 3, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Financiamento', class: 'negative-value', signal: '-' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar crédito com type_code = 4' do
      finance_movement = create(:finance_movement, type_code: 4, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Crédito', class: 'positive-value', signal: '+' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar Recebimento empréstimo com type_code = 5' do
      finance_movement = create(:finance_movement, type_code: 5, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Recebimento Empréstimo', class: 'positive-value', signal: '+' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar Vendas com type_code = 6' do
      finance_movement = create(:finance_movement, type_code: 6, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Vendas', class: 'positive-value', signal: '+' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar Recebimento TED com type_code = 7' do
      finance_movement = create(:finance_movement, type_code: 7, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Recebimento TED', class: 'positive-value', signal: '+' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar Recebimento Doc com type_code = 8' do
      finance_movement = create(:finance_movement, type_code: 8, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Recebimento DOC', class: 'positive-value', signal: '+' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
    it 'deve retornar Aluguel com type_code = 9' do
      finance_movement = create(:finance_movement, type_code: 9, store_financial_movement: @store_financial,
                                                   finance_report: @finance_report)
      response_expected = { message: 'Aluguel', class: 'negative-value', signal: '-' }
      expect(finance_movement.transcation_type).to eq(response_expected)
    end
  end
end
