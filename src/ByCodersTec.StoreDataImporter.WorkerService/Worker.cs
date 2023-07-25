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

namespace ByCodersTec.StoreDataImporter.WorkerService
{
    public class Worker : BackgroundService
    {
        private ILogger<Worker> _logger;
        private IConnection _connection;
        private IModel _channel;
        private bool RabbitConnected;

        public Worker(ILogger<Worker> logger)
        {
            _logger = logger;
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
                _channel.QueueDeclare(queue: "transaction", durable: false, exclusive: false, autoDelete: false, arguments: null);
                RabbitConnected = true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Task.Delay(1000).Wait();
            }
        }

        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            while (!stoppingToken.IsCancellationRequested)
            {
                //_logger.LogInformation("Worker running at: {time}", DateTimeOffset.Now);
                //await Task.Delay(1000, stoppingToken);
                if (!RabbitConnected)
                {
                    ConnectToRabbit();
                } else
                {
                    var consumer = new EventingBasicConsumer(_channel);
                    consumer.Received += (model, ea) =>
                    {
                        var body = ea.Body.ToArray();
                        var message = Encoding.UTF8.GetString(body);
                        CnabImportViewModel cnabTransaction = Newtonsoft.Json.JsonConvert.DeserializeObject<CnabImportViewModel>(message);

                        StructureMapContainer.Instance.container.GetInstance<ITransactionService>().AddTransaction(new AddTransactionRequest { model = cnabTransaction });

                        Console.WriteLine(" [x] Received from Rabbit: {0}", message);
                    };
                    _channel.BasicConsume(queue: "transaction",
                                            autoAck: true,
                                            consumer: consumer);
                    _channel.BasicConsume(queue: "transaction", autoAck: true, consumer: consumer);
                }
            }
        }
    }
}
