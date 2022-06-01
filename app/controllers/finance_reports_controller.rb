class FinanceReportsController < ApplicationController
  respond_to :html, :json
  before_action :set_finance_report, only: [:show, :destroy]
  before_action :recalculate_store_balance, only: [:destroy]

  def index
    @finance_reports = FinanceReport.all

    respond_with(@finance_reports)
  end

  def show
    @finance_report_movement = @finance_report.finance_movements
    @stores_for_select = @finance_report_movement.pluck(:store_name).uniq
    if params[:store_filter]
      @finance_report_movement = @finance_report_movement.where(store_name: params[:store_filter] ) unless params[:store_filter] == ""
      @filtered_store = params[:store_filter]
    else
      @filtered_store = ""
    end
    respond_with(@finance_report)
  end

  def new
    @finance_report = FinanceReport.new
    respond_with(@finance_report)
  end

  def create
    @finance_report = FinanceReport.create(title: params[:finance_title])

    errors = []
    File.foreach(finance_file_params) do |line|
      begin
        attributes = helpers.finance_movement_constructor(line)
        finance_movement = FinanceMovement.new(attributes)
      rescue
        errors << line
      end
    end

    if errors.empty?
      respond_with(@finance_report, status: :created , notice: 'Relatório Criado com sucesso')
    else
      respond_with(errors, location: new_finance_report_path, status: :unprocessable_entity, notice: 'Linhas com erro no relatório')
    end
  end

  def destroy
    @finance_report.destroy
    redirect_to finance_reports_path, notice: "Relatório excluido com sucesso"
  end

  private

  def recalculate_store_balance
    helpers.recalculate_store_balance
  end

  def finance_file_params
    params.permit(:finance_file).require(:finance_file)
  end

  def set_finance_report
    @finance_report = FinanceReport.find(params[:id])
  end
end
