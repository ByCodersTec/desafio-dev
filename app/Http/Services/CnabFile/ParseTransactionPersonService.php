<?php

namespace App\Http\Services\CnabFile;

use App\Http\Services\Utils\ValidateCpfService;
use Exception;

class ParseTransactionPersonService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 19;
	private const POSITION_TYPE_TRANSACTION_END = 11;

	public function execute(array $infoCnab)
	{
		$cpfPersonTransaction = array_slice($infoCnab,$this::POSITION_TYPE_TRANSACTION_INIT,$this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$cpfPersonTransaction = implode($cpfPersonTransaction);

		if(!ValidateCpfService::execute($cpfPersonTransaction)){
			throw new Exception("CPF inválido, tente novamente");
		}

		return $cpfPersonTransaction;
	}
}
