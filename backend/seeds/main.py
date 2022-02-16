from providers.api import API
from seeds import get_seeds

api = API()

if __name__ == "__main__":
    for seed_schema, seeds in get_seeds().items():
        print(f"Inserting {seed_schema} ...")
        for seed in seeds:
            print("Sending seed ...")
            _, res = api.post("transactions-types", seed)

            if _:
                print("Created with success!")
            else:
                print(res)
