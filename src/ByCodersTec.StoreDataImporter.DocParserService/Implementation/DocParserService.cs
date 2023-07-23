using ByCodersTec.StoreDataImporter.ViewModel;
using System.Reflection;
using System.Text.RegularExpressions;

namespace ByCodersTec.StoreDataImporter.DocParserService.Implementation
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
                if (!string.IsNullOrEmpty(item.ValidationPattern) && !Regex.IsMatch(value.ToString(), item.ValidationPattern))
                {
                    throw new Exception("Validation Error");
                }

                propertyInfo.SetValue(response, Convert.ChangeType(value, propertyInfo.PropertyType), null);
            }

            return response;
        }

        public List<T> ParseFileLinseFromString<T>(List<string> fileLine, List<DocColumnViewModel> columns, bool zeroBased)
        {
            var listType = typeof(List<>);
            var constructedListType = listType.MakeGenericType(typeof(T));

            List<T> response = (List<T>)Activator.CreateInstance(constructedListType);

            foreach (var item in fileLine)
            {
                response.Add(ParseFileLinseFromString<T>(item, columns, zeroBased));
            }

            return response;
        }
    }
}