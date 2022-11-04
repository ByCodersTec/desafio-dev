<?php

namespace App\Http\Controllers\Company;

use App\Http\Controllers\Controller;
use App\Http\Resources\Companies\CompaniesResource;
use App\Http\Services\Company\ShowCompanyWithCnabFileService;
use Illuminate\Http\Request;

class ShowCompanyWithCnabFileController extends Controller
{
 /**
 *  @OA\Schema(
 *     schema="cnab_files_show",
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
 *     path="/show/companies/{company_id}",
 *     description="Index companies with CNAB files",
 *     @OA\Response(
 *         response=200,
 *         description="OK",
	* @OA\Parameter(
	*    description="ID of company",
	*    in="path",
	*    name="company_id",
	*    required=true,
	*    example="934afafa-c6b4-4658-aa0a-863c7cccb947",
	*    @OA\Schema(
	*       type="integer",
	*       format="int64"
	*    )
	* ),
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
 *
 *
 *     )
 * )
 */
	public function show(ShowCompanyWithCnabFileService $service, Request $request)
	{
		try {
			return new CompaniesResource($service->execute($request->company_id));
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}
}
