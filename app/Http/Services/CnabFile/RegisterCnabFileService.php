<?php

namespace App\Http\Services\CnabFile;

use App\Http\Dtos\CnabFile\CreateCnabFileDto;
use App\Repositories\Interfaces\CnabFile\CnabFileRepositoryInterface;
use Exception;

class RegisterCnabFileService
{
	private CnabFileRepositoryInterface $cnabFileRepository;

	public function __construct(CnabFileRepositoryInterface $cnabFileRepository)
	{
		$this->cnabFileRepository = $cnabFileRepository;
	}

	public function execute(CreateCnabFileDto $createCnabFileDto)
	{
		$cnabFileCreated = $this->cnabFileRepository->create($createCnabFileDto);

		if(!$cnabFileCreated){
			throw new Exception('Erro ao criar registro do arquivo');
		}

		return $cnabFileCreated;
	}
}
