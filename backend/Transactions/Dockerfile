FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /app
COPY Transactions.Api/Transactions.Api.csproj Transactions.Api/
RUN dotnet restore Transactions.Api/Transactions.Api.csproj

COPY . .
RUN dotnet publish Transactions.Api/Transactions.Api.csproj -c Release -o out

FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS runtime
WORKDIR /app
COPY --from=build /app/out .

RUN apt-get update && apt-get install -y coreutils
CMD ["sh", "-c", "sleep 7 && dotnet Transactions.Api.dll"]

EXPOSE 80
EXPOSE 443