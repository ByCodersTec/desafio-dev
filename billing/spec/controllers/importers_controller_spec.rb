require 'rails_helper'

RSpec.describe 'ImporterController', type: :request do
  context 'when try make a post with txt file and' do
    let!(:path) { Rails.root.join('spec', 'fixtures', 'files', 'file_example.txt') }
    let!(:file) { Rack::Test::UploadedFile.new(path) }

    it 'return successful' do
      post '/importer/import_file', params: { file: file }
      json_response = JSON.parse(@response.body)

      expect(@response.code).to eq('200')
      expect(json_response).to eq({
        'success' => true,
        'message' => 'Upload and creation data make with successful!'
      })
    end
  end

  context 'when try make a post with another extension file and' do
    let!(:path) { Rails.root.join('spec', 'fixtures', 'files', 'rails.png') }
    let!(:file) { Rack::Test::UploadedFile.new(path) }

    it 'return fails' do
      post '/importer/import_file', params: { file: file }
      json_response = JSON.parse(@response.body)

      expect(json_response).to eq({
        'success' => false,
        'message' => 'Something is wrong!'
      })
    end
  end
end