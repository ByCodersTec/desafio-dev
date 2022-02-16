from providers.api import API


class StoresRepository(API):

    endpoint = "stores"

    def get_store(self, store_id: str):
        _, res = self.get(f"{self.endpoint}/{store_id}")

        if not _:
            print(res)
            return False

        return res

    def create_store(self, data: dict):
        try:
            _, res = self.post(self.endpoint, data)

            if not _:
                print(res)
                return False

            return res
        except Exception as e:
            print(e)
            return False
