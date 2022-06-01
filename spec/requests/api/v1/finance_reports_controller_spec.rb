# frozen_string_literal: true

require 'rails_helper'

RSpec.describe 'Api::V1::FinanceReportsControllers', type: :request do
  before(:each) do
    @finance_report = create(:finance_report)
    @store_financial = create(:store_financial_movement)
    @finance_movement = create(:finance_movement, type_code: 1, store_financial_movement: @store_financial,
                                                  finance_report: @finance_report)
  end

  describe 'GET /index' do
    it 'Deve retornar array relatórios financeiros' do
      get '/api/v1/finance_reports'
      expect(response).to have_http_status(200)
      expect(response.body).to eq([@finance_report].to_json)
    end
  end

  describe 'GET /Show' do
    it 'Deve retornar relatório financeiro do ID informado' do
      get "/api/v1/finance_reports/#{@finance_report.id}"
      expect(response).to have_http_status(200)
      expect(response.body).to eq(@finance_report.to_json)
    end
  end

  describe 'Delete /Destroy' do
    it 'Deve retornar relatório financeiro do ID informado' do
      delete "/api/v1/finance_reports/#{@finance_report.id}"
      expect(response).to have_http_status(200)
      expect(response.body).to eq({ "message": 'Relatório financeiro excluido com sucesso' }.to_json)
    end
  end

  describe 'GET /finance_movements' do
    it 'Deve retornar array de movimentações financeiras' do
      get "/api/v1/finance_reports/#{@finance_report.id}/finance_movements"
      expect(response).to have_http_status(200)
      expect(response.body).to eq([@finance_movement].to_json)
    end
  end

  describe 'GET /finance_movements/:id' do
    it 'Deve retornar movimentação financeira do ID informado' do
      get "/api/v1/finance_reports/#{@finance_report.id}/finance_movements/#{@finance_movement.id}"
      expect(response).to have_http_status(200)
      expect(response.body).to eq(@finance_movement.to_json)
    end
  end
end
