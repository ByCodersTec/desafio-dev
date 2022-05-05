class TransactionsController < ApplicationController
  before_action :transactions_params

  def index
    transactions = []
    @providers = Provider.all

    @providers.each do |provider|
      transactions_by_provider = Transaction.where(provider_id: provider.id)
      provider_name = capitalize_name(provider.name_provider)
      provider_owner = capitalize_name(provider.name_owner)
      total_balance = transactions_by_provider.map(&:value).sum

      transactions << {
        provider_name: provider_name,
        provider_owner: provider_owner,
        total_balance: "R$#{total_balance.to_s}"
      }
    end

    render json: { success: true, transactions: transactions }
  end

  private

  def capitalize_name(name)
    name.split(' ')&.map { |x| x.capitalize }.join(' ')
  end

  def default_error
    render json: { success: false, message: 'Something is wrong!' }
  end

  def transactions_params
    params.permit(:id)
  end
end