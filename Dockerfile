FROM ruby:3.1.2
RUN apt-get update -qq && apt-get install -y postgresql-client
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg -o /root/yarn-pubkey.gpg && apt-key add /root/yarn-pubkey.gpg
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" > /etc/apt/sources.list.d/yarn.list
RUN apt-get update && apt-get install -y --no-install-recommends nodejs yarn
RUN mkdir /cnab
WORKDIR /cnab
COPY Gemfile /cnab/Gemfile
COPY Gemfile.lock /cnab/Gemfile.lock
RUN gem install rails bundler && bundle install
RUN yarn install
COPY . /cnab
ENTRYPOINT ["./entrypoints/docker-entrypoint.sh"]
