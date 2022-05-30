Rails.application.routes.draw do
  root to: "finance_reports#new"

  resources :finance_reports do
    resources :finance_movements
  end

  namespace :api do
    namespace :v1 do
      resources :finance_reports do
        resources :finance_movements
      end
    end
  end
end
