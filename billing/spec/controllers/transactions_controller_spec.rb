require 'rails_helper'

RSpec.describe 'TransactionsController', type: :request do
  context 'when make request GET and' do
    let!(:path) { Rails.root.join('spec', 'fixtures', 'files', 'file_example.txt') }
    let(:data) do
      [
        { "provider_name"=>"Bar Do João", "provider_owner"=>"João Macedo", "total_balance"=>"R$406.0" },
        { "provider_name"=>"Loja Do Ó - Matriz", "provider_owner"=>"Maria Josefina", "total_balance"=>"R$434.0" },
        { "provider_name"=>"Mercado Da Avenida", "provider_owner"=>"Marcos Pereira", "total_balance"=>"R$2335.2" },
        { "provider_name"=>"Mercearia 3 Irmãos", "provider_owner"=>"José Costa", "total_balance"=>"R$7023.0" },
        { "provider_name"=>"Loja Do Ó - Filial", "provider_owner"=>"Maria Josefina", "total_balance"=>"R$152.32" }
      ]
    end

    before do
      Importer.new(path).import_and_create_data
    end

    it 'return successful with array with transactions' do
      get '/transactions'
      json_response = JSON.parse(@response.body)

      expect(@response.code).to eq('200')
      expect(json_response).to eq({
        'success' => true,
        'transactions' => data
      })
    end
  end

  context 'when make request GET and havent anyone register and' do
    let!(:path) { Rails.root.join('spec', 'fixtures', 'files', 'rails.png') }
    let!(:file) { Rack::Test::UploadedFile.new(path) }

    it 'return successful with empty array' do
      get '/transactions'
      json_response = JSON.parse(@response.body)

      expect(json_response).to eq({
        'success' => true,
        'transactions' => []
      })
    end
  end
end
