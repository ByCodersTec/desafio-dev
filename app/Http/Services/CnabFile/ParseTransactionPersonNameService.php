<?php

namespace App\Http\Services\CnabFile;

class ParseTransactionPersonNameService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 48;
	private const POSITION_TYPE_TRANSACTION_END = 14;

	public function execute(array $infoCnab)
	{
		$transactionPersonName = array_slice($infoCnab, $this::POSITION_TYPE_TRANSACTION_INIT, $this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$transactionPersonName = explode("   ", implode($transactionPersonName));

		return $transactionPersonName[0];
	}
}
