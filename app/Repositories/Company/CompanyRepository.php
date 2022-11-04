<?php

namespace App\Repositories\Company;

use App\Http\Dtos\Company\CreateCompanyDto;
use App\Models\Company;
use App\Repositories\Interfaces\Company\CompanyRepositoryInterface;
use Exception;

class CompanyRepository implements CompanyRepositoryInterface
{
	private Company $model;

	public function __construct(Company $model)
	{
		$this->model = $model;
	}

	public function create(CreateCompanyDto $createCompanyDto): ?Company
	{
		try {
			return $this->model->create($createCompanyDto->toArray()) ?? null;
		} catch (\Throwable $th) {
			throw new Exception('Não foi possível criar o registro da empresa');
		}
	}

	public function findCompanyByCpfPerson(string $cpf): ?Company
	{
		try {
			return $this->model->where('person_cpf',$cpf)->first() ?? null;
		} catch (\Throwable $th) {
			throw new Exception('Erro ao buscar empresa');
		}
	}

	public function indexCompanyWithCnabFile(): ?Collection
	{
		try {
			return $this->model->with('cnabFiles')->orderBy('legal_person_name')->get();
		} catch (\Throwable $th) {
			throw new Exception('Erro ao buscar empresa');
		}
	}

	public function showCompanyWithCnabFile(string $id): ?Company
	{
		try {
			return $this->model::with('cnabFiles','cnabFiles.typeTransaction')->find($id);
		} catch (\Throwable $th) {
			throw new Exception('Erro ao buscar empresa');
		}
	}
}
