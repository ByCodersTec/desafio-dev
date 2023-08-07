using AutoMapper;
using System.Text.RegularExpressions;
using Transactions.Application.ViewModels;
using Transactions.Domain.Models;

namespace Transactions.Application.AutoMapper
{
    public class ModelToViewModelMappingProfile : Profile
    {
        public ModelToViewModelMappingProfile()
        {
            #region Store
            CreateMap<StoreModel, StoreViewModel>()
            .ForMember(vm => vm.Balance, opt => opt.MapFrom(m => Convert.ToDouble(m.Balance) / 100));
            #endregion

            #region Operation
            CreateMap<OperationModel, OperationViewModel>()
                .ForMember(vm => vm.Transaction, opt => opt.MapFrom(m => Regex.Replace(m.TransactionType.ToString(), "([A-Z](?=[a-z]))", " $1").TrimStart()))
                .ForMember(vm => vm.Value, opt => opt.MapFrom(m => Convert.ToDouble(m.Value) / 100));
            #endregion
        }
    }
}
