from django.core.exceptions import ValidationError
from django.test import TestCase
from .models import Transaction, TransactionError
from .utils import cnab_line_to_object, save_lines

class TransactionModelTest(TestCase):
    def prepare(self, entry_data):
        try:
            result = cnab_line_to_object(entry_data)
        except ValidationError as ex:
            result = TransactionError(description=entry_data, detail=str(ex.message_dict))
        except ValueError as ex:
            result = TransactionError(description=entry_data, detail=str(ex))
        return result


    def test_valid_line(self):
        """
        Test if valid line will be converted to object
        """
        entry_data = '220190302011111111322222222222333333333333055555JOÃO MACEDZ   BAR DO JOÃOZ    '
        expected_result = 'CPF:22222222222/Card:333333333333 paid $1111111.13 at 2019-03-02 05:55:55-03:00 to BAR DO JOÃOZ(JOÃO MACEDZ)'
        transaction_obj = self.prepare(entry_data)
        self.assertEquals(type(transaction_obj), Transaction)

        result = str(transaction_obj)
        self.assertEquals(result, expected_result)


    def test_line_with_more_characters(self):
        """
        Test if convert line even if it has a lot of characters
        """
        entry_data = '320190302911111111322222222222333333333333055555JOÃO MACEDZ   BAR DO JOÃOZ      ALGO A MAIS NOS CARACTERES'
        expected_result = 'CPF:22222222222/Card:333333333333 paid $91111111.13 at 2019-03-02 05:55:55-03:00 to BAR DO JOÃOZ(JOÃO MACEDZ)'
        result = str(self.prepare(entry_data))
        self.assertEquals(result, expected_result)

    def test_invalid_short_line(self):
        """
        Test returns error if line is too short
        """
        entry_data = '12019030291111111132222222222233333333333305555'
        expected_result = TransactionError(description=entry_data, detail=str({'store_owner': ['Este campo não pode estar vazio.'], 'store': ['Este campo não pode estar vazio.']}))
        result = self.prepare(entry_data)
        self.assertEquals(type(result), type(expected_result))
        self.assertEquals(result.description, expected_result.description)
        self.assertEquals(result.detail, expected_result.detail)


    def test_invalid_line_type_transaction(self):
        """
        Test transaction type is not valid
        """
        entry_data = 'A20190302911111111322222222222333333333333055555JOÃO MACEDZ   BAR DO JOÃOZ      '
        expected_result = TransactionError(description=entry_data, detail=str({'type': ["Valor 'A' não é uma opção válida."]}))
        result = self.prepare(entry_data)
        self.assertEquals(type(result), type(expected_result))
        self.assertEquals(result.description, expected_result.description)
        self.assertEquals(result.detail, expected_result.detail)


    def test_invalid_lines(self):
        """
        Test transaction type is not valid and is short (multi message)
        """
        entry_data = 'A201903029111111113222222222223333333333330555'
        expected_result = TransactionError(description=entry_data, detail=str({'type': ["Valor 'A' não é uma opção válida."],'store_owner': ['Este campo não pode estar vazio.'], 
                                                                               'store': ['Este campo não pode estar vazio.']}))
        result = self.prepare(entry_data)
        self.assertEquals(type(result), type(expected_result))
        self.assertEquals(result.description, expected_result.description)
        self.assertEquals(result.detail, expected_result.detail)


    def test_invalid_date(self):
        """
        Test date is not valid
        """
        entry_data = '2A0190302011111111322222222222333333333333055555JOÃO MACEDZ   BAR DO JOÃOZ    '
        expected_result = TransactionError(description=entry_data, detail="time data 'A0190302055555-0300' does not match format '%Y%m%d%H%M%S%z'")
        result = self.prepare(entry_data)
        self.assertEquals(type(result), type(expected_result))
        self.assertEquals(result.description, expected_result.description)
        self.assertEquals(result.detail, expected_result.detail)


    def test_invalid_nin(self):
        """
        Test NIN/CPF is not valid
        """
        entry_data = '22019030201111111132222222222A333333333333055555JOÃO MACEDZ   BAR DO JOÃOZ    '
        expected_result = TransactionError(description=entry_data, detail=str({'nin': ['Invalid NIN']}))
        result = self.prepare(entry_data)
        self.assertEquals(type(result), type(expected_result))
        self.assertEquals(result.description, expected_result.description)
        self.assertEquals(result.detail, expected_result.detail)


    def test_invalid_card(self):
        """
        Test Card is not valid
        """
        entry_data = '2201903020111111113222222222223333333X3333055555JOÃO MACEDZ   BAR DO JOÃOZ    '
        expected_result = TransactionError(description=entry_data, detail=str({'card': ['Invalid Card']}))
        result = self.prepare(entry_data)
        self.assertEquals(type(result), type(expected_result))
        self.assertEquals(result.description, expected_result.description)
        self.assertEquals(result.detail, expected_result.detail)


    def test_insert_data(self):
        """
        Test manual insert data
        """
        entry_data = '220190302011111111322222222222333333333333055555JOÃO TESTADO   BAR DO TESTE    '
        transaction_obj = self.prepare(entry_data)
        result = str(transaction_obj)
        expected_result = 'CPF:22222222222/Card:333333333333 paid $1111111.13 at 2019-03-02 05:55:55-03:00 to BAR DO TESTE(JOÃO TESTADO)'
        self.assertEquals(result, expected_result)
        transaction_obj.clean_fields()
        transaction_obj.save()
        self.assertNotEquals(transaction_obj.id, 0)


    def test_utils_cnab_line_to_object(self):
        """
        Test manual insert data using cnab_line_to_object
        """
        entry_data = '220190302011111111322222222222333333333333055555JOÃO TESTADO   BAR DO TESTE    '
        transaction_obj = cnab_line_to_object(entry_data)
        self.assertEquals(type(transaction_obj), Transaction)
        transaction_obj.clean_fields()
        transaction_obj.save()
        self.assertNotEquals(transaction_obj.id, 0)


    def test_utils_save_lines_str(self):
        """
        Test insert multiple datas using save_lines (only success and using string)
        """
        entry_data = [
            '220190302011111111322222222222333333333333055555JOÃO TESTADO   BAR DO TESTE    ', 
            '220190302000011111322222222222333333333333055655JOÃO2TESTADO   LOJA TESTE      ']
        transaction_objs = save_lines(entry_data)
        self.assertEquals(len(transaction_objs.get("successes", [])), 2)
        self.assertEquals(len(transaction_objs.get("errors", [])), 0)


    def test_utils_save_lines_bytes(self):
        """
        Test insert multiple datas using save_lines (only success and using bytes)
        """
        entry_data = [
            bytes('220190302011111111322222222222333333333333055555JOÃO TESTADO   BAR DO TESTE    ', encoding='utf-8'), 
            bytes('220190302000011111322222222222333333333333055655JOÃO2TESTADO   LOJA TESTE      ', encoding='utf-8')]
        transaction_objs = save_lines(entry_data)
        self.assertEquals(len(transaction_objs.get("successes", [])), 2)


    def test_utils_save_lines_bytes_with_error(self):
        """
        Test insert multiple datas using save_lines (successes and errors and using bytes)
        """
        entry_data = [
            bytes('220190302011111111322222222222333333333333055555JOÃO TESTADO   BAR DO TESTE    ', encoding='utf-8'), 
            bytes('22019030200001111132222222222233333333X333055655JOÃO2TESTADO   LOJA TESTE      ', encoding='utf-8')]
        transaction_objs = save_lines(entry_data)
        self.assertEquals(len(transaction_objs.get("successes", [])), 1)
        self.assertEquals(len(transaction_objs.get("errors", [])), 1)


    def test_utils_save_lines_with_error(self):
        """
        Test insert multiple datas using save_lines (successes and errors and using string)
        """
        entry_data = [
            '220190302011111111322222222222333333333333055555JOÃO TESTADO   BAR DO TESTE    ', 
            '22019030200001111132222222222233333333X333055655JOÃO2TESTADO   LOJA TESTE      ']
        transaction_objs = save_lines(entry_data)
        self.assertEquals(len(transaction_objs.get("successes", [])), 1)
        self.assertEquals(len(transaction_objs.get("errors", [])), 1)
