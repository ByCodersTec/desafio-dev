using ByCodersTec.StoreDataImporter.Domain;
using RabbitMQ.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.QueueService.Rabbit
{
    public interface IRabbitMessageService : IMessageService
    {
        bool Enqueue<T>(T message, IBasicProperties props);
    }
}
