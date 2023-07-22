using ByCodersTec.StoreDataImporter.Entities;
using ByCodersTec.StoreDataImporter.Repository.EF;
using ByCodersTec.StoreDataImporter.Repository.EF.Implementation;
using ByCodersTec.StoreDataImporter.Services.Implementation;
using ByCodersTec.StoreDataImporter.Services.Interfaces;
using StructureMap;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Services
{
    public sealed class StructureMapContainer
    {
        public Container container;
        private StructureMapContainer() {
            container = new Container(_ =>
            {
                _.For<IAppContext>().Use<Repository.EF.AppContext>();
                _.For<IUnitOfWork>().Use<UnitOfWork>();
                _.For<IUserRepository>().Use<UserRepository>();
                _.For<IUserService>().Use<UserService>();
            });
        }
        private static StructureMapContainer instance = null;
        public static StructureMapContainer Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new StructureMapContainer();
                }
                return instance;
            }
        }
    }
}
