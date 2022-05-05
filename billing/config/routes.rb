Rails.application.routes.draw do
  resources :importer, only: [:index] do
    collection { post :import_file, via: :options }
  end
  
  resources :transactions, only: [:index]
end
