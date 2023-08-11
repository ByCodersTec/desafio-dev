source 'https://rubygems.org'
git_source(:github) { |repo| "https://github.com/#{repo}.git" }

ruby '3.2.2'

gem 'bootsnap', require: false
gem 'importmap-rails'
gem 'pg', '~> 1.1'
gem 'puma', '~> 5.0'
gem 'rails', '~> 7.0.7'
gem 'redis', '~> 4.0'
gem 'sassc-rails'
gem 'sprockets-rails'
gem 'stimulus-rails'
gem 'turbo-rails'
gem 'tzinfo-data', platforms: %i[mingw mswin x64_mingw jruby]

group :development, :test do
  gem 'pry'
  gem 'factory_girl_rails'
  gem 'rspec-rails', '~> 3.5'
end

group :development do
  gem 'rubocop', '~> 1.56'
  gem 'solargraph', '~> 0.49.0'
  gem 'web-console'
end

group :test do
  gem 'capybara'
  gem 'selenium-webdriver'
  gem 'webdrivers'
end
