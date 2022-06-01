class Api::V1::FinanceMovementsController < Api::V1::ApiController
  
  before_action :set_finance_movement, only: [:show, :destroy]

  def index
    if params[:store_financial_movement_id].present?
      @finance_movements = FinanceMovement.where(store_financial_movement_id: params[:store_financial_movement_id])
    else
      @finance_movements = FinanceMovement.where(finance_report_id: params[:finance_report_id])
    end
    render json: @finance_movements
  end

  def show
    render json: @finance_movement
  end

  private

  def set_finance_movement
    @finance_movement = FinanceMovement.find(params[:id])
  end
end
