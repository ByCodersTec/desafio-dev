class SalesController < ApplicationController
  def index
  end

  def new
    @sale = Sale.new
    render :new
  end

  def create
    begin
      uploaded_file = params[:file]
      File.foreach(uploaded_file.path) do |line|
        sales_params = parse_sale(line)
        sale = Sale.create!(sales_params)
        Rails.logger.info("New sale created: #{sale.inspect}")
      end
      # redirect_to sales_path
    rescue => e
      Rails.logger.error("Sale creation error: #{e.message}")
      flash[:error] = "There was an error creating the sales: #{e.message}"
      # redirect_to new_sale_path
    end
  end
  
  private
  
  def parse_sale(line)
    {
      type: line[0].to_i,
      occ_day: line[1..8],
      value: line[9..18].to_f / 100,
      cpf: line[19..29],
      card_number: line[30..41].to_s,
      occ_hour: line[42..47],
      store_owner: line[48..61].strip,
      store_name: line[62..80].strip
    }
  end
end
