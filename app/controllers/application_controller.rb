# frozen_string_literal: true

require 'application_responder'

class ApplicationController < ActionController::Base
  self.responder = ApplicationResponder
  respond_to :html

  before_action :set_time_zone

  def set_time_zone
    Time.zone = 'Brasilia'
  end

  def route_not_found
    render file: 'public/404.html', status: :not_found, layout: false
  end
end
