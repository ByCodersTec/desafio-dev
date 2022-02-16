from providers.api import API


class ParserStatusRepository(API):
    enpoint = "parser-status"

    def update_parser_status(self, id, status, message="", count=0):
        _, res = self.put(f"{self.enpoint}/{id}", {
            "status": status,
            "message": message,
            "count": count
        })

        print(res)
