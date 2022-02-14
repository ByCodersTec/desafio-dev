import uuid


class Transactions:

    transactionTypeId: str = ""
    transactionType: str = ""
    storeId: str = ""
    value: float = 0
    occurence: str = ""
    datestring: str = ""
    timestring: str = ""
    buyerIdentification: str = ""
    creditCard: str = ""
    storeOwner: str = ""
    storeName: str = ""

    def __init__(self, block):
        for f, v in block.items():
            if hasattr(self, f):
                setattr(self, f, v)
        self.setStoreId()
        self.setOccurence()

    def getTransactionTypeId(self) -> str:
        return self.transactionTypeId

    def getTransactionType(self) -> str:
        return self.transactionType

    def setStoreId(self):
        self.storeId = str(uuid.uuid5(uuid.NAMESPACE_X500, self.storeName))

    def getStoreId(self) -> str:
        return self.storeId

    def getValue(self) -> float:
        return self.value

    def setOccurence(self):
        self.occurence = f"{self.getDatestring()} {self.getTimestring()}"

    def getOccurence(self) -> str:
        return self.occurence

    def getDatestring(self) -> str:
        datestring = self.datestring
        year = datestring[:4]
        month = datestring[4:6]
        day = datestring[6:]
        return f"{year}-{month}-{day}"

    def getTimestring(self) -> str:
        timestring = self.timestring
        hour = timestring[:2]
        mins = timestring[2:4]
        secs = timestring[4:]
        return f"{hour}:{mins}:{secs}"

    def getBuyerIdentification(self) -> str:
        return self.buyerIdentification

    def getCreditCard(self) -> str:
        return self.creditCard

    def getStoreOwner(self) -> str:
        return self.storeOwner

    def getStoreName(self) -> str:
        return self.storeName
