# frozen_string_literal: true

require 'rails_helper'

RSpec.describe FinanceReportsHelper, type: :helper do
  describe 'Teste de calculo' do
    before(:each) do
      @finance_report = create(:finance_report)
      @store_financial = create(:store_financial_movement, balance: 0)
    end

    it 'Recalculo de saldo de Loja' do
      finance_movement_1 = create(:finance_movement, type_code: 1, value: 150.00,
                                                     store_financial_movement: @store_financial, finance_report: @finance_report)
      finance_movement_2 = create(:finance_movement, type_code: 2, value: 200.00,
                                                     store_financial_movement: @store_financial, finance_report: @finance_report)
      helper.recalculate_store_balance(@finance_report.id)
      expect(@store_financial.reload.balance).to eq(50)
    end

    it 'Recalculo de saldo de Loja deve excluir caso balance = 0' do
      finance_movement_1 = create(:finance_movement, type_code: 1, value: 150.00,
                                                     store_financial_movement: @store_financial, finance_report: @finance_report)
      finance_movement_2 = create(:finance_movement, type_code: 2, value: 150.00,
                                                     store_financial_movement: @store_financial, finance_report: @finance_report)
      helper.recalculate_store_balance(@finance_report.id)
      expect(StoreFinancialMovement.where(id: @store_financial.id).first).to be(nil)
    end

    it 'Calculo de saldo por loja' do
      finance_movement_1 = create(:finance_movement, type_code: 1, value: 250.00,
                                                     store_financial_movement: @store_financial, finance_report: @finance_report)
      finance_movement_2 = create(:finance_movement, type_code: 2, value: 150.00,
                                                     store_financial_movement: @store_financial, finance_report: @finance_report)
      expect(helper.final_balance([finance_movement_1, finance_movement_2])).to eq(100)
    end
  end
end
