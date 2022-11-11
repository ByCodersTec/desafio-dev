<?php

namespace App\Http\Controllers\Company;

use App\Http\Controllers\Controller;
use App\Http\Resources\Companies\CompaniesResource;
use App\Http\Services\Company\IndexCompanyWithCnabFileService;

class IndexCompanyWithCnabFileController extends Controller
{
 /**
 *  @OA\Schema(
 *     schema="cnab_files",
 *
 *   	@OA\Property(property="id", type="string"),
 *       @OA\Property(property="type_id", type="string"),
 *       @OA\Property(property="type_name", type="string"),
 *       @OA\Property(property="type_operation", type="string"),
 *       @OA\Property(property="transaction_date", type="float"),
 *       @OA\Property(property="transaction_value", type="string"),
 *       @OA\Property(property="transaction_card", type="string"),
 *       @OA\Property(property="transaction_hour", type="string"),
 *
 * )
 *
 * @OA\Get(
 *     path="/index/companies",
 *     description="Index companies with CNAB files",
 *     @OA\Response(
 *         response=200,
 *         description="OK",
 *    @OA\JsonContent(
 *       @OA\Property(property="id", type="string"),
 *       @OA\Property(property="legal_person_name", type="string"),
 *       @OA\Property(property="person_name", type="string"),
 *       @OA\Property(property="person_cpf", type="string"),
 *       @OA\Property(property="account_balance", type="float"),
 *       @OA\Property(property="importations_cnab_files", type="array", @OA\Items(ref="#/components/schemas/cnab_files")))
 *        )
 * ),
 *
 *     @OA\Response(
 *         response=422,
 *         description="Erro ao buscar empresas",
 *			@OA\JsonContent(
 *       		@OA\Property(property="message", type="string")
 *        )
 *
 *     )
 * )
 */
	public function index(IndexCompanyWithCnabFileService $service)
	{
		try {
			return CompaniesResource::collection($service->execute());
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}

	public function indexView(IndexCompanyWithCnabFileService $service)
	{
		try {
			$companies = CompaniesResource::collection($service->execute());
			return view('listCnabImports',["companies" => $companies]);
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}
}
