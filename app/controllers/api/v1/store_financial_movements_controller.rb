class Api::V1::StoreFinancialMovementsController < Api::V1::ApiController

  before_action :set_store_report, only: [:show, :destroy]

  def index 
    @store_reports = StoreFinancialMovement.all
    render json: @store_reports
  end

  def show
    render json: @store_report
  end

  private

  def set_store_report
    @store_report = StoreFinancialMovement.find(params[:id])
  end
end
