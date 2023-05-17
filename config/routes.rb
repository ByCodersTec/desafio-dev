Rails.application.routes.draw do
  get 'sales/index'
  root 'sales#new'

  resources :sales, only: [:new, :create]

end