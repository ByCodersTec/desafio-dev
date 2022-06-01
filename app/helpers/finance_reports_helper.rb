module FinanceReportsHelper
  def finance_movement_constructor(line)
    type = line[0].to_i
    date = line[1..8]
    value = line[9..18].to_f / 100
    cpf = line[19..29]
    card = line[30..41]
    time = line[42..47]
    owner_name = line[48..61].strip
    store_name = line[62..80].strip.chomp
    datetime = time_constructor(date, time)
    finance_report_id = @finance_report.id
    store_financial_constructor(store_name)
    calculate_store_balance(type, value)
    store_financial_movements_id = @store.id
    { type_code: type, value: value, cpf: cpf, card: card, store_owner: owner_name, store_name: store_name,
      register_date: datetime, finance_report_id: finance_report_id, store_financial_movement_id: store_financial_movements_id }
  end

  def time_constructor(date, time)
    datetime = DateTime.new(date[0..3].to_i, date[4..5].to_i, date[6..7].to_i, time[0..1].to_i, time[2..3].to_i,
                            time[4..5].to_i, '-03:00')
  end

  def final_balance(finance_movements)
    balance = 0
    finance_movements.each do |finance|
      if [1, 4, 5, 6, 8].include? finance.type_code
        balance += finance.value
      else
        balance -= finance.value
      end
    end
    balance
  end

  def calculate_store_balance(type, value)
    @store.balance = 0 if @store.balance.nil?
    if [1, 4, 5, 6, 8].include? type
      @store.balance += value
    else
      @store.balance -= value
    end
    @store.save
  end

  def store_financial_constructor(store_name)
    @store = StoreFinancialMovement.find_or_create_by(store_name: store_name)
  end

  def recalculate_store_balance(finance_report_id)
    finance_report = FinanceReport.find(finance_report_id)
    store_from_report = finance_report.finance_movements.group_by(&:store_financial_movement_id)
    store_from_report.each do |id, movements|
      current_store = StoreFinancialMovement.find(id)
      movements.each do |movement|
        if [1, 4, 5, 6, 8].include? movement.type_code
          current_store.balance -= movement.value
        else
          current_store.balance += movement.value
        end
        current_store.balance.round == 0 ? current_store.destroy : current_store.save
      end
    end
  end
end
