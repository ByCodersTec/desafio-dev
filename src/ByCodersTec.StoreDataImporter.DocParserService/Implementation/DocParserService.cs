using ByCodersTec.StoreDataImporter.DocParserService.Message;
using ByCodersTec.StoreDataImporter.DocParserService.ViewModel;
using ByCodersTec.StoreDataImporter.Domain;
using ByCodersTec.StoreDataImporter.ViewModel;
using System.Reflection;
using System.Text.RegularExpressions;

namespace ByCodersTec.StoreDataImporter.DocParserService.Implementation
{
    public class DocParserService : IDocParserService
    {
        public ParseDocLineResponse<T> ParseFileLinseFromString<T>(ParseDocLineRequest request)
        {
            var LineResult = new DocParseLineResultViewModel<T>();
            LineResult.LineContent = request.DocLine.LineContent;
            T ParsedLineItem = (T)Activator.CreateInstance(typeof(T), new object[] { });

            foreach (var item in request.DocLine.Columns)
            {
                PropertyInfo propertyInfo = ParsedLineItem.GetType().GetProperty(item.ClassPropName);

                var lenght = item.Length;
                if ((item.Start + item.Length) > request.DocLine.LineContent.Length)
                    lenght = request.DocLine.LineContent.Length - item.Start + 1;

                object value = request.DocLine.LineContent.Substring(item.Start - 1, lenght).Trim();
                if (!string.IsNullOrEmpty(item.ValidationPattern) && !Regex.IsMatch(value.ToString(), item.ValidationPattern))
                    item.IsValid = false;
                else
                    item.IsValid = true;

                propertyInfo.SetValue(ParsedLineItem, Convert.ChangeType(value, propertyInfo.PropertyType), null);
                LineResult.Columns.Add(item);
            }

            LineResult.ParsedLineItem = ParsedLineItem;
            return new ParseDocLineResponse<T> { result = LineResult };
        }

        public ParseDocResponse<T> ParseFileLinseFromString<T>(ParseDocRequest request)
        {
            var response = new ParseDocResponse<T>();
            foreach (var item in request.DocLine)
            {
                var line = ParseFileLinseFromString<T>(new ParseDocLineRequest { DocLine = item }).result;
                response.result.Lines.Add(line);
            }

            return response;
        }
    }
}