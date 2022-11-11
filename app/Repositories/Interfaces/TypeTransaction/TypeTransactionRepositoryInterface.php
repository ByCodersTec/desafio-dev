<?php

namespace App\Repositories\Interfaces\TypeTransaction;

use App\Models\TypeTransaction;

interface TypeTransactionRepositoryInterface
{
	public function findByCode(int $code): ?TypeTransaction;
}
