#See https://aka.ms/customizecontainer to learn how to customize your debug container and how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:3.1 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:3.1 AS build
WORKDIR /src
COPY ["ByCodersTec.StoreDataImporter.Oauth.IdentityServer/ByCodersTec.StoreDataImporter.Oauth.IdentityServer.csproj", "ByCodersTec.StoreDataImporter.Oauth.IdentityServer/"]
RUN dotnet restore "ByCodersTec.StoreDataImporter.Oauth.IdentityServer/ByCodersTec.StoreDataImporter.Oauth.IdentityServer.csproj"
COPY . .
WORKDIR "/src/ByCodersTec.StoreDataImporter.Oauth.IdentityServer"
RUN dotnet build "ByCodersTec.StoreDataImporter.Oauth.IdentityServer.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ByCodersTec.StoreDataImporter.Oauth.IdentityServer.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
EXPOSE 80
EXPOSE 443
ENTRYPOINT ["dotnet", "ByCodersTec.StoreDataImporter.Oauth.IdentityServer.dll"]