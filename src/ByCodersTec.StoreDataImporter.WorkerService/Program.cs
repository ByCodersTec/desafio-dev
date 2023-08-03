using ByCodersTec.StoreDataImporter.WorkerService;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

IHost host = Host.CreateDefaultBuilder(args)
    .ConfigureServices(services =>
    {
        services.AddHostedService<Worker>();
        services.AddSingleton<IReadMessage, ReadMessage>();
    })
    .Build();

await host.RunAsync();