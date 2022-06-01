# frozen_string_literal: true

module Api
  module V1
    class StoreFinancialMovementsController < Api::V1::ApiController
      before_action :set_store_report, only: %i[show destroy]

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
  end
end
