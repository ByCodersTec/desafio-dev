<?php

namespace App\Repositories\TypeTransaction;

use App\Models\TypeTransaction;
use App\Repositories\Interfaces\TypeTransaction\TypeTransactionRepositoryInterface;
use Exception;

class TypeTransactionRepository implements TypeTransactionRepositoryInterface
{
	private TypeTransaction $model;

	public function __construct(TypeTransaction $model)
	{
		$this->model = $model;
	}

	public function findByCode(int $code): ?TypeTransaction
	{
		try {
			return $this->model::where('transaction_code','=',$code)->first() ?? null;
		} catch (\Throwable $th) {
			throw new Exception('Erro ao buscar tipo de transação');
		}
	}

}
