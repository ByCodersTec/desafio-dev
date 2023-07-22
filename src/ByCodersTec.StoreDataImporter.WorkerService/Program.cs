// See https://aka.ms/new-console-template for more information
using ByCodersTec.StoreDataImporter.Services;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using ByCodersTec.StoreDataImporter.Services.Message;
using ByCodersTec.StoreDataImporter.ViewModel;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using System.Text;

Console.WriteLine("Hello, World!");

if (Boolean.TryParse(Environment.GetEnvironmentVariable("Development"), out bool isDev))
{
    if (isDev)
    {
        var appContext = StructureMapContainer.Instance.container.GetInstance<ByCodersTec.StoreDataImporter.Repository.EF.AppContext>();
        //appContext.Database.EnsureCreated();
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
//Console.ReadLine();
await Task.Run(() => Thread.Sleep(Timeout.Infinite));