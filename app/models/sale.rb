class Sale < ApplicationRecord
  self.inheritance_column = :sale_type
  validates :occ_day, presence: true
  validates :value, presence: true
  validates :cpf, presence: true
  validates :card_number, presence: true
  validates :occ_hour, presence: true
  validates :store_owner, presence: true
  validates :store_name, presence: true
end