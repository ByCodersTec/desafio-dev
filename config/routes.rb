Rails.application.routes.draw do
  root to: "finance_reports#new"

  resources :finance_reports do
    resources :finance_movements
  end

  resources :store_financial_movements do
    resources :finance_movements
  end

  namespace :api do
    namespace :v1 do
      match '*unmatched', to: 'api#route_not_found', via: :all
      resources :finance_reports do
        resources :finance_movements
      end
      resources :store_financial_movements do
        resources :finance_movements
      end
    end
  end
end
