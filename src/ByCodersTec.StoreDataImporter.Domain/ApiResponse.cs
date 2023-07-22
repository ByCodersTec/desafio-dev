using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public class ApiResponse<T>
    {
        public T data { get; set; }
        public bool status { get; set; }
        public System.Net.HttpStatusCode code { get; set; }
        public string error_message { get; set; }
        public List<BusinessRule> brokenRules { get; set; }

        public static ApiResponse<T> CreateResponse(bool status, string message, T data, System.Net.HttpStatusCode code = System.Net.HttpStatusCode.Created, List<BusinessRule> rules = null)
        {
            var response = new ApiResponse<T>();
            response.status = status;
            response.error_message = message;
            response.data = data;
            response.brokenRules = rules;
            response.code = code;
            return response;
        }

        public static ApiResponse<T> CreateResponse(T data)
        {
            var response = new ApiResponse<T>();
            response.status = true;
            response.error_message = "";
            response.data = data;
            response.brokenRules = null;
            response.code = System.Net.HttpStatusCode.Created;
            return response;
        }
    }

}
