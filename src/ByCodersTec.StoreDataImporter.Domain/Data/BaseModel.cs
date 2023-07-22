using System.ComponentModel.DataAnnotations.Schema;

namespace ByCodersTec.StoreDataImporter.Domain
{
    public abstract class BaseModel<TId> : IBaseModel
    {
        public TId Id { get; set; }
        public DateTime createdAt { get; set; }
        public DateTime updatedAt { get; set; }

        public BaseModel()
        {
            this.brokedRules = new List<BusinessRule>();
        }

        public bool isValid()
        {
            return this.brokedRules.Count == 0;
        }

        [NotMapped]
        private List<BusinessRule> brokedRules { get; set; }

        public List<BusinessRule> getBrokedRules()
        {
            return this.brokedRules;
        }

        public void addBrokedRules(BusinessRule brokedRule)
        {
            this.brokedRules.Add(brokedRule);
        }

        public abstract bool validate();
    }

}