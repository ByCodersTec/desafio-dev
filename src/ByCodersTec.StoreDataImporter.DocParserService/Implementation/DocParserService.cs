using ByCodersTec.StoreDataImporter.ViewModel;
using System.Reflection;

namespace ByCodersTec.StoreDataImporter.DocParserService
{
    public class DocParserService : IDocParserService
    {
        public T ParseFileLinseFromString<T>(string fileLine, List<DocColumnViewModel> columns, bool zeroBased)
        {
            T response = (T)Activator.CreateInstance(typeof(T), new object[] {});

            foreach (var item in columns)
            {
                PropertyInfo propertyInfo = response.GetType().GetProperty(item.ClassPropName);
                var lenght = item.Lenght;
                
                if ((item.Start + item.Lenght) > fileLine.Length)
                    lenght = fileLine.Length - item.Start + 1;

                object value = fileLine.Substring(item.Start - 1, lenght).Trim();
                propertyInfo.SetValue(response, Convert.ChangeType(value, propertyInfo.PropertyType), null);
            }

            return response;
        }
    }
}