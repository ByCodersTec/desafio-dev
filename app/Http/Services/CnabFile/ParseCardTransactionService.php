<?php

namespace App\Http\Services\CnabFile;

use Exception;

class ParseCardTransactionService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 30;
	private const POSITION_TYPE_TRANSACTION_END = 12;

	public function execute(array $infoCnab)
	{
		$transactionCard = array_slice($infoCnab,$this::POSITION_TYPE_TRANSACTION_INIT,$this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$transactionCard = implode($transactionCard);

		// Verifica se foi informada uma sequência de digitos repetidos.
		if (preg_match('/(\d)\1{10}/', $transactionCard)) {
			throw new Exception("Cartão informado é inválido");
		}

		return $transactionCard;
	}
}
