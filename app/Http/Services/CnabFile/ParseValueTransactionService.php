<?php

namespace App\Http\Services\CnabFile;

use Exception;

class ParseValueTransactionService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 9;
	private const POSITION_TYPE_TRANSACTION_END = 10;

	public function execute(array $infoCnab)
	{
		$valueTransaction = array_slice($infoCnab,$this::POSITION_TYPE_TRANSACTION_INIT,$this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$valueTransaction = (float) implode($valueTransaction);
		$valueTransaction = $valueTransaction/100;

		if(!$valueTransaction){
			throw new Exception("O valor da transação não é valido");
		}

		return $valueTransaction;
	}
}
