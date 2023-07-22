﻿using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Message;
using ByCodersTec.StoreDataImporter.ViewModel;
using Microsoft.Extensions.Logging;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.WorkerService
{
    public class TimedHostedService : IHostedService, IDisposable
    {
        private readonly ILogger _logger;
        private Timer _timer;

        public TimedHostedService(ILogger<TimedHostedService> logger)
        {
            _logger = logger;
        }

        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("Timed Background Service is starting.");

            _timer = new Timer(DoWork, null, TimeSpan.Zero,
                TimeSpan.FromSeconds(5));

            return Task.CompletedTask;
        }

        private void DoWork(object state)
        {
            _logger.LogInformation("Timed Background Service is working.");
            if (Boolean.TryParse(Environment.GetEnvironmentVariable("Development"), out bool isDev))
            {
                if (isDev)
                {
                    var appContext = StructureMapContainer.Instance.container.GetInstance<ByCodersTec.StoreDataImporter.Repository.EF.AppContext>();
                    appContext.Database.EnsureCreated();
                    //appContext.Seed();
                }
            }

            Task.Delay(1000).Wait();
            Console.WriteLine("Consuming Queue Now");

            ConnectionFactory factory = new ConnectionFactory() { HostName = "rabbitmq", Port = 5672 };
            factory.UserName = "guest";
            factory.Password = "guest";
            IConnection conn = factory.CreateConnection();
            IModel channel = conn.CreateModel();
            channel.QueueDeclare(queue: "hello",
                                    durable: false,
                                    exclusive: false,
                                    autoDelete: false,
                                    arguments: null);

            var consumer = new EventingBasicConsumer(channel);
            consumer.Received += (model, ea) =>
            {
                var body = ea.Body.ToArray();
                var message = Encoding.UTF8.GetString(body);
                UserViewModel user = Newtonsoft.Json.JsonConvert.DeserializeObject<UserViewModel>(message);

                StructureMapContainer.Instance.container.GetInstance<IUserService>().AddUser(new AddUserRequest { model = user });

                Console.WriteLine(" [x] Received from Rabbit: {0}", message);
            };
            channel.BasicConsume(queue: "hello",
                                    autoAck: true,
                                    consumer: consumer);

            Console.WriteLine("passed");
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("Timed Background Service is stopping.");

            _timer?.Change(Timeout.Infinite, 0);

            return Task.CompletedTask;
        }

        public void Dispose()
        {
            _timer?.Dispose();
        }
    }
}
