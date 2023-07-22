#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/runtime:6.0 AS base
WORKDIR /app

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["ByCodersTec.StoreDataImporter.WorkerService/ByCodersTec.StoreDataImporter.WorkerService.csproj", "ByCodersTec.StoreDataImporter.WorkerService/"]
RUN dotnet restore "ByCodersTec.StoreDataImporter.WorkerService/ByCodersTec.StoreDataImporter.WorkerService.csproj"
COPY . .
WORKDIR "/src/ByCodersTec.StoreDataImporter.WorkerService"
RUN dotnet build "ByCodersTec.StoreDataImporter.WorkerService.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ByCodersTec.StoreDataImporter.WorkerService.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "ByCodersTec.StoreDataImporter.WorkerService.dll"]