class Provider < ApplicationRecord
  validate :name_provider_valid, on: %i[create]

  def name_provider_valid
    return errors.add(:base, "Nome da Loja #{self.name_provider} já cadastrado") if name_provider_exists?
  end

  private

  def name_provider_exists?
    return false unless Provider.where('lower(name_provider) LIKE ?', "%#{self.name_provider.downcase}%").present?

    true
  end
end
