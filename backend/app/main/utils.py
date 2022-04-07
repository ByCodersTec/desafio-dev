def serialize(itens):
    response = []
    for item in itens:
        try:
            item_serialized = item.__dict__
            try:
                item_serialized.pop('_sa_instance_state')
            except:
                ...
            response.append(item_serialized)
        except:
            ...
    return response