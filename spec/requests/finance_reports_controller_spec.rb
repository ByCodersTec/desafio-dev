require 'rails_helper'

RSpec.describe 'FinanceReports', type: :request do
  describe 'Teste #Index' do
    it 'Teste respond_with Html' do
      get '/finance_reports'
      expect(response).to have_http_status(200)
      expect(response).to render_template(:index)
    end

    it 'Teste respond_with Json' do
      report = create(:finance_report)
      get '/finance_reports', params: { format: :json }
      expect(response).to have_http_status(200)
      expect(response.body).to eq([report].to_json)
    end
  end

  describe 'Teste #New' do
    it 'Teste respond_with Html' do
      get '/finance_reports/new'
      expect(response).to have_http_status(200)
      expect(response).to render_template(:new)
    end

    it 'Teste respond_with Json' do
      get '/finance_reports/new', params: { format: :json }
      expect(response).to have_http_status(200)
    end
  end

  describe 'Teste #Show' do
    it 'Teste respond_with Html' do
      report = create(:finance_report)
      get "/finance_reports/#{report.id}"
      expect(response).to have_http_status(200)
      expect(response).to render_template(:show)
    end

    it 'Teste respond_with Json' do
      report = create(:finance_report)
      get "/finance_reports/#{report.id}", params: { format: :json }
      expect(response).to have_http_status(200)
      expect(response.body).to eq(report.to_json)
    end
  end

  describe 'Teste #Create' do
    before(:all) do
      file_path_success = Rails.root.join('spec', 'CNAB.txt')
      @finance_file_success = Rack::Test::UploadedFile.new(file_path_success, 'text/plain')
      file_path_errors = Rails.root.join('spec', 'CNAB with errors.txt')
      @finance_file_errors = Rack::Test::UploadedFile.new(file_path_errors, 'text/plain')
    end

    it 'Teste respond_with Html with success' do
      post '/finance_reports', params: { finance_file: @finance_file_success }
      expect(response).to have_http_status(302)
      expect(response).to redirect_to(assigns(:finance_report))
    end

    it 'Teste respond_with Html with errors' do
      post '/finance_reports', params: { finance_file: @finance_file_errors }
      expect(response).to have_http_status(302)
      expect(response).to redirect_to(new_finance_report_path)
    end

    it 'Teste respond_with JSON with success' do
      post '/finance_reports', params: { finance_file: @finance_file_success, format: :json }
      expect(response).to have_http_status(201)
    end

    it 'Teste respond_with JSON with errors' do
      post '/finance_reports', params: { finance_file: @finance_file_errors, format: :json }
      expect(response).to have_http_status(201)
    end
  end

  describe 'Teste #Destroy' do
    it 'Teste respond_with Html' do
      report = create(:finance_report)
      delete "/finance_reports/#{report.id}"
      expect(response).to have_http_status(302)
      expect(response).to redirect_to(finance_reports_url)
    end

    it 'Teste respond_with Json' do
      report = create(:finance_report)
      delete "/finance_reports/#{report.id}", params: { format: :json }
      expect(response).to have_http_status(302)
      expect(response).to redirect_to(finance_reports_url)
    end
  end
end
