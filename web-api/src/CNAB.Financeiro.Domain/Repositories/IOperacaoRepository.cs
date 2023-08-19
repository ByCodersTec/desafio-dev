using CNAB.Core.Data;
using CNAB.Financeiro.Domain.Entities;
using Microsoft.EntityFrameworkCore.ChangeTracking;

namespace CNAB.Financeiro.Domain.Repositories
{
    public interface IOperacaoRepository : IRepository<Operacao>
    {
        Task<List<Loja>> GetListLojasAsync(int page = 0, int limit = 0);
        Task<Loja?> GetListLojaByNameAsync(string name);
        Task<List<Operacao>> GetListOperacoesByLojaAsync(Guid id, int page = 0, int limit = 0);
        Task<List<Operacao>> GetListOperacoesByLojaNoPaginationAsync(Guid id);
        Task<TipoTransacao?> GetTipoTransacaoById(int id);
        Task<EntityEntry<Operacao>> AddOperacaoAsync(Operacao entity);
        Task<EntityEntry<Loja>> AddLojaAsync(Loja entity);
    }
}
