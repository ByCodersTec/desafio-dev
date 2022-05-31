require "application_responder"

class ApplicationController < ActionController::Base
  self.responder = ApplicationResponder
  respond_to :html


  before_action :set_time_zone

  def set_time_zone
    Time.zone = "Brasilia"
  end

end
