<?php

namespace App\Http\Services\CnabFile;

use App\Repositories\Interfaces\TypeTransaction\TypeTransactionRepositoryInterface;
use Exception;

class ParseTypeTransactionService
{
	private const POSITION_TYPE_TRANSACTION_INIT = 1;
	private const POSITION_TYPE_TRANSACTION_END = 1;

	private TypeTransactionRepositoryInterface $typeTransactionRepository;

	public function __construct(TypeTransactionRepositoryInterface $typeTransactionRepository)
	{
		$this->typeTransactionRepository = $typeTransactionRepository;
	}

	public function execute(array $infoCnab)
	{
		$typeTransactionCode = array_slice($infoCnab,0,$this::POSITION_TYPE_TRANSACTION_INIT) ?? 0;

		$typeTransaction = $this->typeTransactionRepository->findByCode((int)$typeTransactionCode[0]);

		if(!$typeTransaction){
			throw new Exception('Não foi possível localizar o tipo de operação, tente novamente', 400);
		}

		return $typeTransaction->id;
	}

}
