<?php

namespace App\Http\Services\CnabFile;

use Exception;

class ParseTransactionLegalPersonNameService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 62;
	private const POSITION_TYPE_TRANSACTION_END = 19;

	public function execute(array $infoCnab)
	{
		$transactionLegalPersonName = array_slice($infoCnab, $this::POSITION_TYPE_TRANSACTION_INIT, $this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$transactionLegalPersonName = explode("   ", implode($transactionLegalPersonName));

		return $transactionLegalPersonName[0];
	}
}
