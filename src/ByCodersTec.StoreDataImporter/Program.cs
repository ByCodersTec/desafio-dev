using ByCodersTec.StoreDataImporter.DocParserService;
using ByCodersTec.StoreDataImporter.DocParserService.Implementation;
using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.QueueService.Rabbit;
using ByCodersTec.StoreDataImporter.Repository.EF;
using ByCodersTec.StoreDataImporter.Repository.EF.Implementation;
using ByCodersTec.StoreDataImporter.Services.Implementation;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using MediatR;
using ByCodersTec.StoreDataImporter;
using ByCodersTec.StoreDataImporter.WebSocketService.SignalRChatService;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<ByCodersTec.StoreDataImporter.Repository.EF.AppContext>();

builder.Services.AddTransient<IAppContext, ByCodersTec.StoreDataImporter.Repository.EF.AppContext>();
builder.Services.AddTransient<IUnitOfWork, UnitOfWork>();

//Registering Repositories
builder.Services.AddTransient<IUserRepository, UserRepository>();
builder.Services.AddTransient<ITransactionRepository, TransactionRepository>();
builder.Services.AddTransient<ITransactionTypeRepository, TransactionTypeRepository>();
builder.Services.AddTransient<IStoreRepository, StoreRepository>();
builder.Services.AddTransient<IDocDefinitionRepository, DocDefinitionRepository>();
builder.Services.AddTransient<IDocDefinitionColumnRepository, DocDefinitionColumnRepository>();

//Registering Services
builder.Services.AddTransient<IUserService, UserService>();
builder.Services.AddTransient<ITransactionService, TransactionService>();
builder.Services.AddTransient<IDocParserService, DocParserService>();
builder.Services.AddTransient<ByCodersTec.StoreDataImporter.Domain.IMessageService, RabbitMessageService>();

builder.Services.AddMediatR(typeof(ByCodersTec.StoreDataImporter.CQRS.Handlers.GetUsersHandler).Assembly);
//builder.Services.AddMediatR(typeof(AddProductCommand));
builder.Services.AddSignalR();

var MyAllowedOrigins = "_myCORS";

builder.Services.AddCors(options =>
{
    options.AddPolicy(name: MyAllowedOrigins,
            policy =>
            {
                policy.WithOrigins("http://localhost:4200, http://localhost:3000, https://host.docker.internal:8080, https://host.docker.internal")
                    .AllowAnyHeader()
                    .AllowAnyMethod()
                    .SetIsOriginAllowed((host) => true)
                    .AllowCredentials();
            });
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    using (var scope = app.Services.CreateScope())
    {
        var appContext = scope.ServiceProvider.GetRequiredService<ByCodersTec.StoreDataImporter.Repository.EF.AppContext>();
        appContext.Database.EnsureCreated();
        appContext.Seed();
    }

    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseAuthorization();

app.MapControllers();
app.UseCors(MyAllowedOrigins);

app.MapHub<ChatHub>("/chat");

app.Run();
