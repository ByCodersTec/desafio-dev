using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.Entities;
using RabbitMQ.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.QueueService.Rabbit
{
    public class RabbitMessageService : IMessageService
    {
        ConnectionFactory _factory;
        IConnection _conn;
        IModel _channel;
        public RabbitMessageService()
        {
            Console.WriteLine("about to connect to rabbit");

            _factory = new ConnectionFactory() { HostName = "rabbitmq", Port = 5672 };
            _factory.UserName = "guest";
            _factory.Password = "guest";
            _conn = _factory.CreateConnection();
            _channel = _conn.CreateModel();
            _channel.QueueDeclare(queue: "transaction",
                                    durable: false,
                                    exclusive: false,
                                    autoDelete: false,
                                    arguments: null);
        }
        public bool Enqueue<User>(User messageString)
        {
            var body = Encoding.UTF8.GetBytes(Newtonsoft.Json.JsonConvert.SerializeObject(messageString));
            _channel.BasicPublish(exchange: "",
                                routingKey: "transaction",
                                basicProperties: null,
                                body: body);
            Console.WriteLine(" [x] Published {0} to RabbitMQ", messageString);
            return true;
        }
    }
}
