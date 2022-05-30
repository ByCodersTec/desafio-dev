module Api::V1
  class ApiController < ApplicationController
    protect_from_forgery with: :null_session

    rescue_from ::ActiveRecord::RecordNotFound, with: :record_not_found

    def record_not_found(exception)
      render json: {error: "Registro não encontrado"}.to_json, status: 404
    end

    def route_not_found
      render json: {error: "Rota Inválida"}.to_json, status: 500
    end
  end
end