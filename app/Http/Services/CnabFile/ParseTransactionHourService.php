<?php

namespace App\Http\Services\CnabFile;

use Carbon\Carbon;
use DateTime;

class ParseTransactionHourService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 42;
	private const POSITION_TYPE_TRANSACTION_END = 6;

	public function execute(array $infoCnab)
	{
		$transactionHours = array_slice($infoCnab, $this::POSITION_TYPE_TRANSACTION_INIT, $this::POSITION_TYPE_TRANSACTION_END) ?? 0;
		$transactionHours = implode($transactionHours);
		$transactionHoursParsed = new Carbon($transactionHours);

		return $transactionHoursParsed->format('H:i:s');
	}
}
