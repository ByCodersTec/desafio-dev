FROM ruby:3.0
RUN apt-get update -qq && apt-get install -y nodejs default-mysql-client libpq-dev
WORKDIR /finance-report
COPY Gemfile /finance-report/Gemfile
COPY Gemfile.lock /finance-report/Gemfile.lock
RUN bundle install


# Node install
RUN curl -sL https://deb.nodesource.com/setup_14.x | bash -
RUN apt -y install nodejs

# Yarn install
RUN npm i -g yarn

# Install dependencies
RUN yarn

# Add a script to be executed every time the container starts.
COPY entrypoint.sh /usr/bin/
RUN chmod +x /usr/bin/entrypoint.sh
ENTRYPOINT ["entrypoint.sh"]
EXPOSE 3000

# Configure the main process to run when running the image
CMD /bin/bash