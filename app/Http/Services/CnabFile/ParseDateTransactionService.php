<?php

namespace App\Http\Services\CnabFile;

use Carbon\Carbon;
use Exception;

class ParseDateTransactionService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 1;
	private const POSITION_TYPE_TRANSACTION_END = 8;

	public function execute(array $infoCnab)
	{
		$dateTransaction = array_slice($infoCnab,$this::POSITION_TYPE_TRANSACTION_INIT,$this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$dateTransaction = new Carbon(implode($dateTransaction));
		$isDate = checkdate($dateTransaction->month,$dateTransaction->day,$dateTransaction->year);

		if(!$isDate){
			throw new Exception("A data da transação não é valida");
		}

		return $dateTransaction->format("Y-m-d");
	}
}
