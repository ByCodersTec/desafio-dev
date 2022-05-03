# README
💻 **Stack**

- Ruby 3.1.1

- Rails 7.0.2

- Host machine dependencies
    - [Docker (CE)](https://docs.docker.com/engine/installation/)
    - [Docker Compose](https://docs.docker.com/compose/install/)

🚀 **To start the Rails app, run:**

- ⚠️ Is it your first time? ⚠️

  - Create a external volume to share gems across containers:
      ```bash
        docker-compose build
      ```
    Note: If your docker commands only work using sudo before, run the following command in the console to fix it:
    - ```bash
        sudo groupadd docker
      ``` 
    - ```bash
        sudo usermod -aG docker $USER
      ``` 
    - ```bash
        sudo service docker restart
      ```
  - Load all needed gems into `gems_ops` external volume:
    ```bash
      docker-compose run --rm app bundle install
    ```
- Starting all stack (_add `-d` flag to run in background_):

  - Follow the `Starting all stack` section
  ```bash
    docker-compose up
  ```
🛠 **Useful commands:**

- Start Rails console:
  ```bash
    docker-compose run --rm app bundle exec rails c
  ```
- Execute create DB:
  ```bash
      docker-compose run --rm app bundle exec rails db:create
    ```
- Execute migration:
  ```bash
    docker-compose run --rm app bundle exec rails db:migrate
  ```
- Execute Webpack:
  ```bash
    docker-compose run --rm app bundle exec rails webpacker:install
  ```
- Access database console:
  ```bash
      docker-compose run --rm app bundle exec rails dbconsole
    ```
- Test a specific file

  ```bash
    docker-compose run --rm app bundle exec rspec ./spec/services/name_file.rb'
  ```