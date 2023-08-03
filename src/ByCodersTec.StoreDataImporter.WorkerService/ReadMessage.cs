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

namespace ByCodersTec.StoreDataImporter.WorkerService
{
    public class ReadMessage : IReadMessage
    {
        private HubConnection hubConnection;

        public bool SignalRConnected { get; private set; }

        public ReadMessage()
        {
            try
            {
                hubConnection = new HubConnectionBuilder()
                .WithUrl("http://host.docker.internal:8080/chat")
                .Build();

                hubConnection.Closed += async (error) =>
                {
                    await Task.Delay(new Random().Next(0, 5) * 1000);
                    await hubConnection.StartAsync();
                };

                SignalRConnected = true;
            }
            catch (Exception ex)
            {
                SignalRConnected = false;
            }
        }

        public void Read()
        {
            // Definition of Connection 
            // Obviously in a real project we mustn't put here the user and password...   
            var _rabbitMQServer = new ConnectionFactory() { Password = "guest", UserName = "guest", HostName = "rabbitmq", Port = 5672 };

            using var connection = _rabbitMQServer.CreateConnection();

            using var channel = connection.CreateModel();

            StartReading(channel, "transaction");
        }

        private void StartReading(IModel channel, string queueName)
        {
            // connect to the queue
            channel.QueueDeclare(queueName,
                durable: true,
                exclusive: false,
                autoDelete: false,
                arguments: null);

            // Consumer definition
            var consumer = new EventingBasicConsumer(channel);

            // Definition of event when the Consumer gets a message
            consumer.Received += (sender, e) => {
                ManageMessageAsync(e, channel, queueName);
            };

            // Start pushing messages to our consumer
            channel.BasicConsume(queueName, true, consumer);

            Console.WriteLine("Consumer is running");
            Console.ReadLine();
        }

        private void ManageMessageAsync(BasicDeliverEventArgs e, IModel channel, string queueName)
        {
            //var body = e.Body.ToArray();
            //var message = Encoding.UTF8.GetString(body);
            //Console.WriteLine(message);

            //// We append the message in a file .txt
            //using StreamWriter file = new("MessagesRead.txt", append: true);
            //file.WriteLine(message);

            var body = e.Body.ToArray();
            var message = Encoding.UTF8.GetString(body);
            CnabImportViewModel cnabTransaction = Newtonsoft.Json.JsonConvert.DeserializeObject<CnabImportViewModel>(message);

            StructureMapContainer.Instance.container.GetInstance<ITransactionService>().AddTransaction(new AddTransactionRequest { model = cnabTransaction });
            
            if (e.BasicProperties.Headers.TryGetValue("is_last_item", out object value))
            {
                bool is_last_item = bool.Parse(value.ToString());
                if (is_last_item)
                {
                    Console.WriteLine("last_item");
                    if (hubConnection.State == HubConnectionState.Disconnected)
                    {
                        hubConnection.StartAsync().Wait();
                    }
                    hubConnection.InvokeAsync("SendMessage", "sytem", "reload").Wait();
                }
            }
        }
    }
}
