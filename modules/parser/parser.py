def parser(file_path) -> dict:
    with open(file_path, 'r') as file:
        for line in file:
            splited_text = line
            # print(splited_text)
            parsed = {
                "type": splited_text[0],
                "data": splited_text[1:9],
                "value": "{price:.2f}".format(price = int(splited_text[9:19])/100),
                # "value": splited_text[9:19],
                "cpf": splited_text[19:30],
                "credit_card": splited_text[30:42],
                "hora": splited_text[42:48],
                "store_owner": splited_text[48:62],
                "store_name": splited_text[62:81].replace("\n", ""),
            }

            # for x in parsed:
            #     print(x, len(parsed[x]))

            print(parsed)


parser('data/CNAB.txt')
