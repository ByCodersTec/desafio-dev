blockchain = {
    "transactionType": (1, 1),
    "datestring": (9, 8),
    "value": (19, 10),
    "buyerIdentification": (30, 11),
    "creditCard": (42, 12),
    "timestring": (48, 6),
    "storeOwner": (62, 14),
    "storeName": (81, 19),
}


def get_field_value(v: str, field: str) -> any:
    if not v:
        return v

    if field == "value":
        remove_landing_zeros = v.lstrip("0")
        return float(remove_landing_zeros) / 100
    elif field in ["type", "transactionType"]:
        return int(v)

    return v.strip()


def get_transaction_block(line: str) -> dict:
    block = {}
    for field, positions in blockchain.items():
        __end, __size = positions
        field_value = get_field_value(line[__end-__size:__end], field)

        block.update({
            field: field_value
        })

    return block
