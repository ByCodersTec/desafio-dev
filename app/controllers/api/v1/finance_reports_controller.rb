class Api::V1::FinanceReportsController < Api::V1::ApiController

  before_action :set_finance_report, only: [:show, :destroy]
  before_action :recalculate_store_balance, only: [:destroy]

  def index 
    @finance_reports = FinanceReport.all
    render json: @finance_reports
  end

  def show
    render json: @finance_report
  end

  def destroy
    @finance_report.destroy
    render json: {message: "Relatório financeiro excluido com sucesso"}.to_json, status: :ok
  end

  private

  def recalculate_store_balance
    helpers.recalculate_store_balance(@finance_report.id)
  end

  def set_finance_report
    @finance_report = FinanceReport.find(params[:id])
  end
end
