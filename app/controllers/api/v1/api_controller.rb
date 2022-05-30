module Api::V1
  class ApiController < ApplicationController
    protect_from_forgery with: :null_session
  end
end