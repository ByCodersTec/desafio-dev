class Api::V1::FinanceMovementsController < Api::V1::ApiController
  
  before_action :set_finance_movement, only: [:show, :destroy]

  def index 
    @finance_movements = FinanceMovement.where(finance_report_id: params[:finance_report_id])
    render json: @finance_movements
  end

  def show
    render json: @finance_movement
  end

  def destroy
    @finance_movement.destroy
    render json: {message: "Registro excluido com sucesso"}, status: :ok
  end

  private

  def set_finance_movement
    @finance_movement = FinanceMovement.find(params[:id])
  end
end
