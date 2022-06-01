class StoreFinancialMovementsController < ApplicationController
  def index
    @store_movements = StoreFinancialMovement.all
  end

  def show
    @store_movement = StoreFinancialMovement.find(params[:id])
    @store_finance_movements = @store_movement.finance_movements
  end
end
