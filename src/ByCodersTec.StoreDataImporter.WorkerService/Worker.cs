using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using RabbitMQ.Client.Events;
using RabbitMQ.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Message;
using ByCodersTec.StoreDataImporter.Services;
using ByCodersTec.StoreDataImporter.ViewModel;
using Microsoft.AspNetCore.SignalR.Client;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory.Database;

namespace ByCodersTec.StoreDataImporter.WorkerService
{
    public class Worker : BackgroundService
    {
        private ILogger<Worker> _logger;
        private IConnection _connection;
        private IModel _channel;
        private bool ConsumerAdded;
        public QueueDeclareOk transactionsQueue { get; private set; }
        public string consumerTag { get; private set; }
        public string workerconsumer { get; private set; }

        private bool RabbitConnected;
        HubConnection hubConnection;
        private bool SignalRConnected;
        private readonly IReadMessage _readMessage;

        public Worker(
            ILogger<Worker> logger,
            IReadMessage readMessage
        )
        {
            _logger = logger;
            _readMessage = readMessage;
        }

        private void ConnectToRabbit()
        {
            try
            {
                ConnectionFactory factory = new ConnectionFactory() { HostName = "rabbitmq", Port = 5672 };
                factory.UserName = "guest";
                factory.Password = "guest";
                _connection = factory.CreateConnection();
                _channel = _connection.CreateModel();
                transactionsQueue = _channel.QueueDeclare(queue: "transaction", durable: false, exclusive: false, autoDelete: false, arguments: null);
                RabbitConnected = true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Task.Delay(1000).Wait();
            }
        }

        private void ConnectToSignalR()
        {
            try
            {
                hubConnection = new HubConnectionBuilder()
                .WithUrl("http://localhost:8080/chat")
                .Build();

                hubConnection.Closed += async (error) =>
                {
                    await Task.Delay(new Random().Next(0, 5) * 1000);
                    await hubConnection.StartAsync();
                };

                SignalRConnected = true;
            }
            catch (Exception)
            {
                SignalRConnected = false;
            }
        }

        public uint GetMessageCount(string queueName)
        {
            return _channel.MessageCount(queueName);
        }

        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            while (!stoppingToken.IsCancellationRequested)
            {
                // Run the Read method
                await Task.Run(() => _readMessage.Read());
            }
        }
    }
}
