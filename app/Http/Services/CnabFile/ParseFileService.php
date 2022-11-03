<?php

namespace App\Http\Services\CnabFile;

use App\Http\Dtos\CnabFile\CreateCnabFileDto;
use App\Http\Dtos\Company\CreateCompanyDto;
use App\Http\Services\Company\CreateCompanyService;
use App\Http\Services\Utils\RemoveAccentsService;
use Exception;
use Illuminate\Http\UploadedFile;
use Illuminate\Support\Facades\DB;

class ParseFileService
{
	private ParseTypeTransactionService $parseTypeService;
	private ParseDateTransactionService $parseDateService;
	private ParseValueTransactionService $parseValueService;
	private ParseTransactionPersonService $parseTransactionPersonService;
	private ParseCardTransactionService $parseCardTransactionService;
	private ParseTransactionHourService $parseTransactionHourService;
	private ParseTransactionPersonNameService $parseTransactionPersonNameService;
	private ParseTransactionLegalPersonNameService $parseTransactionLegalPersonNameService;
	private CreateCompanyService $createCompanyService;
	private RegisterCnabFileService $registerCnabFileService;

	public function __construct(
		ParseTypeTransactionService $parseTypeService,
		ParseDateTransactionService $parseDateService,
		ParseValueTransactionService $parseValueService,
		ParseTransactionPersonService $parseTransactionPersonService,
		ParseCardTransactionService $parseCardTransactionService,
		ParseTransactionHourService $parseTransactionHourService,
		ParseTransactionPersonNameService $parseTransactionPersonNameService,
		ParseTransactionLegalPersonNameService $parseTransactionLegalPersonNameService,
		CreateCompanyService $createCompanyService,
		RegisterCnabFileService $registerCnabFileService
	) {
		$this->parseTypeService = $parseTypeService;
		$this->parseDateService = $parseDateService;
		$this->parseValueService = $parseValueService;
		$this->parseTransactionPersonService = $parseTransactionPersonService;
		$this->parseCardTransactionService = $parseCardTransactionService;
		$this->parseTransactionHourService = $parseTransactionHourService;
		$this->parseTransactionPersonNameService = $parseTransactionPersonNameService;
		$this->parseTransactionLegalPersonNameService = $parseTransactionLegalPersonNameService;
		$this->createCompanyService = $createCompanyService;
		$this->registerCnabFileService = $registerCnabFileService;
	}

	public function execute(UploadedFile $cnabFile)
	{
		$result = DB::transaction(function () use ($cnabFile) {
			$contentFile = $cnabFile->getContent();
			$contentFileArray = explode("\r\n", $contentFile);

			$dataResult = [];
			for ($i = 0; $i < sizeof($contentFileArray); $i++) {

				try {

					if (empty($contentFileArray[$i])) {
						continue;
					}

					$contentFileArray[$i] = str_split(RemoveAccentsService::execute($contentFileArray[$i]));
					$dataCnabLine["type_id"] = $this->parseTypeService->execute($contentFileArray[$i]);
					$dataCnabLine["transaction_date"] = $this->parseDateService->execute($contentFileArray[$i]);
					$dataCnabLine["transaction_value"] = $this->parseValueService->execute($contentFileArray[$i]);
					$dataCnabLine["person_cpf"] = $this->parseTransactionPersonService->execute($contentFileArray[$i]);
					$dataCnabLine["transaction_card"] = $this->parseCardTransactionService->execute($contentFileArray[$i]);
					$dataCnabLine["transaction_hour"] = $this->parseTransactionHourService->execute($contentFileArray[$i]);
					$dataCnabLine["person_name"] = $this->parseTransactionPersonNameService->execute($contentFileArray[$i]);
					$dataCnabLine["legal_person_name"] = $this->parseTransactionLegalPersonNameService->execute($contentFileArray[$i]);

					$createCompanyDto = new CreateCompanyDto($dataCnabLine);
					$companyCreated = $this->createCompanyService->execute($createCompanyDto);

					$createCnabFileDto = new CreateCnabFileDto($dataCnabLine);
					$createCnabFileDto->company_id = $companyCreated->id;
					$createdCnabFile = $this->registerCnabFileService->execute($createCnabFileDto);

					if ($companyCreated && $createdCnabFile) {
						$dataResult[] = $dataCnabLine;
					}
				} catch (\Exception $e) {
					$message = "Falha na linha $i, erro: " . $e->getMessage() . " corrija o erro e tente novamente";
					throw new Exception($message, 400);
				}
			}

			return $dataResult;
		});

		return $result;
	}
}
