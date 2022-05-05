# Instala as Gems
bundle check || bundle install

# Roda nosso servidor
bundle exec puma -C config/puma.rb