require 'rails_helper'

RSpec.describe Importer do
  context 'when call the service with file txt and' do
    let!(:path) { Rails.root.join('spec', 'fixtures', 'files', 'file_example.txt') }
    let(:importer) { Importer.new(path) }

    it 'return successful' do
      expect(importer.import_and_create_data).to eq(true)
      expect(Customer.last).to be_present
      expect(Provider.last).to be_present
      expect(Transaction.last).to be_present
    end
  end

  context 'when call the service with another extension file and' do
    let!(:path) { Rails.root.join('spec', 'fixtures', 'files', 'rails.png') }
    let(:importer) { Importer.new(path) }

    it 'return fails' do
      expect(importer.import_and_create_data).to eq(false)
      expect(Customer.last).to be(nil)
      expect(Provider.last).to be(nil)
      expect(Transaction.last).to be(nil)
    end
  end
end