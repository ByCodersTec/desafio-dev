# frozen_string_literal: true

module Rack
  class Attack
    Rack::Attack.cache.store = ActiveSupport::Cache::MemoryStore.new

    # Allow all local traffic
    safelist('allow-localhost') do |req|
      req.ip == '127.0.0.1' || req.ip == '::1'
    end
    # Allow an IP address to make 10 requests every 10 seconds

    throttle('req/ip', limit: 5, period: 5, &:ip)
  end
end
