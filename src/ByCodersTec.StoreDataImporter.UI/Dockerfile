#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443


FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y libpng-dev libjpeg-dev curl libxi6 build-essential libgl1-mesa-glx
RUN curl -sL https://deb.nodesource.com/setup_lts.x | bash -
RUN apt-get install -y nodejs
WORKDIR /src
COPY ["ByCodersTec.StoreDataImporter.UI/ByCodersTec.StoreDataImporter.UI.csproj", "ByCodersTec.StoreDataImporter.UI/"]
RUN dotnet restore "ByCodersTec.StoreDataImporter.UI/ByCodersTec.StoreDataImporter.UI.csproj"
COPY . .
WORKDIR "/src/ByCodersTec.StoreDataImporter.UI"
RUN dotnet build "ByCodersTec.StoreDataImporter.UI.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ByCodersTec.StoreDataImporter.UI.csproj" -c Release -o /app/publish


FROM node:16 AS build-web
COPY ./ByCodersTec.StoreDataImporter.UI/ClientApp/package.json /ByCodersTec.StoreDataImporter.UI/ClientApp/package.json
COPY ./ByCodersTec.StoreDataImporter.UI/ClientApp/package-lock.json /ByCodersTec.StoreDataImporter.UI/ClientApp/package-lock.json
WORKDIR /ByCodersTec.StoreDataImporter.UI/ClientApp
RUN npm ci
COPY ./ByCodersTec.StoreDataImporter.UI/ClientApp/ /ByCodersTec.StoreDataImporter.UI/ClientApp
RUN npm run build

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "ByCodersTec.StoreDataImporter.UI.dll"]
