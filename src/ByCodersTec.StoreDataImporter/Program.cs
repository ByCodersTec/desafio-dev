using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.QueueService.Rabbit;
using ByCodersTec.StoreDataImporter.Repository.EF;
using ByCodersTec.StoreDataImporter.Repository.EF.Implementation;
using ByCodersTec.StoreDataImporter.Services.Implementation;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

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

//Registering Services
builder.Services.AddTransient<IUserService, UserService>();
builder.Services.AddTransient<ByCodersTec.StoreDataImporter.Domain.IMessageService, RabbitMessageService>();

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

app.Run();
