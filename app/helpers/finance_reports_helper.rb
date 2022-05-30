module FinanceReportsHelper

  def finance_movement_constructor(line)
    type = line[0].to_i
    date = line[1..8]
    value = line[9..18].to_f / 100
    cpf = line[19..29]
    card = line[30..41]
    time = line[42..47]
    owner_name = line[48..61].strip()
    store_name = line[62..80].strip().chomp()
    datetime = time_constructor(date, time)
    finance_report_id = @finance_report.id
    {type_code: type, value: value, cpf: cpf, card: card, store_owner: owner_name, store_name: store_name, register_date: datetime, finance_report_id: finance_report_id}
  end

  def time_constructor(date, time)
    datetime = DateTime.new(date[0..3].to_i, date[4..5].to_i, date[6..7].to_i, time[0..1].to_i, time[2..3].to_i, time[4..5].to_i, '-03:00')
  end
end
