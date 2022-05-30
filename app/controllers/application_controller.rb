class ApplicationController < ActionController::Base

  before_action :set_time_zone

  def set_time_zone
    Time.zone = "Brasilia"
  end

end
