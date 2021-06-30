from .models import Transaction, TransactionError
from django.utils import timezone
from django.core.files.uploadedfile import InMemoryUploadedFile
from django.core.exceptions import ValidationError

def verify_cnab_file(request_file: InMemoryUploadedFile):
    error_list = []
    transaction_list = []
    for cnab_line in request_file.readlines():
        cnab_line = cnab_line.decode("utf-8")
        transaction = cnab_line_to_object(cnab_line)
        try:
            transaction.clean_fields()
            transaction_list.append(transaction)
        except ValidationError as ex:
            print(ex)
            print(cnab_line)
            error_list.append(TransactionError(description=cnab_line))
    Transaction.objects.bulk_create(transaction_list)
    TransactionError.objects.bulk_create(error_list)


def cnab_line_to_object(cnab_line:str) -> Transaction:
    try:
        datetime_cnab = timezone.datetime.strptime(cnab_line[1:9] + cnab_line[42:48] + "-0300", '%Y%m%d%H%M%S%z')
        return Transaction(
            type = cnab_line[0],
            datetime = datetime_cnab,
            amount = "{:.2f}".format(float(int(cnab_line[9:19])/100)),
            nin = cnab_line[19:30],
            card = cnab_line[30:42],
            store_owner = cnab_line[48:62].strip(),
            store = str(cnab_line[62:80]).strip(),
        )
    except Exception as e:
        raise e