require 'rails_helper'

RSpec.describe FinanceReport, type: :model do
  it { should have_many(:finance_movements) }
end
