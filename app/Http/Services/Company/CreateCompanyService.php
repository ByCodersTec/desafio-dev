<?php

namespace App\Http\Services\Company;

use App\Http\Dtos\Company\CreateCompanyDto;
use App\Repositories\Interfaces\Company\CompanyRepositoryInterface;
use Exception;

class CreateCompanyService
{
	private CompanyRepositoryInterface $companyRepository;

	public function __construct(CompanyRepositoryInterface $companyRepository)
	{
		$this->companyRepository = $companyRepository;
	}

	public function execute(CreateCompanyDto $createCompanyDto)
	{
		$isExist = $this->companyRepository->findCompanyByCpfPerson($createCompanyDto->person_cpf);

		if(!$isExist){
			$companyCreated = $this->companyRepository->create($createCompanyDto);

			if(!$companyCreated){
				throw new Exception('Erro ao criar registro da empresa',400);
			}

			return $companyCreated;
		}

		 return $isExist;
	}
}
