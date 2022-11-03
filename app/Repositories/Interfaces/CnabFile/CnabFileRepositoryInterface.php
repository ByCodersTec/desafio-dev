<?php

namespace App\Repositories\Interfaces\CnabFile;

use App\Http\Dtos\CnabFile\CreateCnabFileDto;
use App\Models\CnabFile;

interface CnabFileRepositoryInterface
{
	public function create(CreateCnabFileDto $createCnabFiledto): ?CnabFile;
}
