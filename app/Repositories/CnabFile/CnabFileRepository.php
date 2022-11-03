<?php

namespace App\Repositories\CnabFile;

use App\Http\Dtos\CnabFile\CreateCnabFileDto;
use App\Models\CnabFile;
use App\Repositories\Interfaces\CnabFile\CnabFileRepositoryInterface;
use Exception;

class CnabFileRepository implements CnabFileRepositoryInterface
{
	private CnabFile $model;

	public function __construct(CnabFile $model)
	{
		$this->model = $model;
	}

	public function create(CreateCnabFileDto $createCnabFiledto): ?CnabFile
	{

			try {
				$cnabFileCreated = $this->model->create($createCnabFiledto->toArray());

				if(!$cnabFileCreated){
					return null;
				}

				$cnabFileCreated->companies()->attach([$createCnabFiledto->company_id]);

				return $cnabFileCreated;
			} catch (\Throwable $th) {
				throw new Exception('Não foi possível criar o registro do arquivo');
			}

	}
}
