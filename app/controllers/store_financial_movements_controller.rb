class StoreFinancialMovementsController < ApplicationController
  
  def index
    @store_movements = StoreFinancialMovement.all
  end

  def show
    @finance_report = StoreFinancialMovement.find(params[:id])
    @finance_report_movement = @finance_report.finance_movements
    @stores_for_select = @finance_report_movement.pluck(:store_name).uniq
    if params[:store_filter]
      @finance_report_movement = @finance_report_movement.where(store_name: params[:store_filter] ) unless params[:store_filter] == ""
      @filtered_store = params[:store_filter]
    else
      @filtered_store = ""
    end
  end
end
