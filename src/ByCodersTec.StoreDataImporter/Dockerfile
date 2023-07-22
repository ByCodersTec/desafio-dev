#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.
# escape=`
FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["ByCodersTec.StoreDataImporter/ByCodersTec.StoreDataImporter.csproj", "ByCodersTec.StoreDataImporter/"]
RUN dotnet restore "ByCodersTec.StoreDataImporter/ByCodersTec.StoreDataImporter.csproj"
COPY . .
WORKDIR "/src/ByCodersTec.StoreDataImporter"
RUN dotnet build "ByCodersTec.StoreDataImporter.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ByCodersTec.StoreDataImporter.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "ByCodersTec.StoreDataImporter.dll"]