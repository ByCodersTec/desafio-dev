using ByCodersTec.StoreDataImporter.Domain;

namespace ByCodersTec.StoreDataImporter.Entities
{
    public class User : BaseModel<Guid>
    {
        public string Name { get; set; }
        public string Email { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }

        public override bool validate()
        {
            return true;
        }
    }
}