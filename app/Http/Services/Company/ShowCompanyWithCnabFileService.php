<?php

namespace App\Http\Services\Company;

use App\Repositories\Interfaces\Company\CompanyRepositoryInterface;
use Exception;

class ShowCompanyWithCnabFileService
{
	private CompanyRepositoryInterface $companyRepository;

	public function __construct(CompanyRepositoryInterface $companyRepository)
	{
		$this->companyRepository = $companyRepository;
	}

	public function execute(string $companyId)
	{
		$company = $this->companyRepository->showCompanyWithCnabFile($companyId);

		if(!$company){
			throw new Exception('Erro ao buscar empresas',400);
		}

			$company->account_balance = 0;
			foreach ($company->cnabFiles as $cnabFile) {
				if($cnabFile->typeTransaction->operation_type == "input"){
					$company->account_balance += $cnabFile->transaction_value;
				}

				if($cnabFile->typeTransaction->operation_type == "output"){
					$company->account_balance -= $cnabFile->transaction_value;
				}
			}


		return $company;
	}
}
