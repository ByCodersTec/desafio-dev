Rails.application.routes.draw do
  root to: "finance_reports#new"

  resources :finance_reports do
    resources :finance_movements
  end
end
