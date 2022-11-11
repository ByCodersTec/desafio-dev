<?php

namespace App\Http\Controllers\CnabFile;

use App\Http\Controllers\Controller;
use App\Http\Requests\CnabFile\UploadCnabFileRequest;
use App\Http\Services\CnabFile\ParseFileService;
use Illuminate\Http\Request;

class UploadCnabFileController extends Controller
{
	public function uploadForm()
	{
		try {
			return view('uploadFileForm');
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}

/**
 * @OA\Post(
 *     path="/upload/file",
 *     description="Upload the CNAB File",
 *     @OA\Parameter(
 *         name="cnab_file",
 *         in="path",
 *         description="Cnab File",
 *         required=true,
 *     ),
 *     @OA\Response(
 *         response=200,
 *         description="OK",
 *    @OA\JsonContent(
 *       @OA\Property(property="id", type="string"),
 *       @OA\Property(property="transaction_date", type="string"),
 *       @OA\Property(property="transaction_value", type="float"),
 *       @OA\Property(property="person_cpf", type="string"),
 *       @OA\Property(property="transaction_card", type="string"),
 *       @OA\Property(property="transaction_hour", type="string"),
 *       @OA\Property(property="person_name", type="string"),
 *       @OA\Property(property="legal_person_name", type="string")
 *        )
 * ),
 *
 *     @OA\Response(
 *         response=422,
 *         description="The given data was invalid.",
 *			@OA\JsonContent(
 *       		@OA\Property(property="message", type="string")
 *        )
 *
 *     )
 * )
 */
	public function upload(ParseFileService $service,UploadCnabFileRequest $request)
	{
		try {
			return $service->execute($request->file('cnab_file'));
		} catch (\Throwable $th) {
			if ($request->is('api/*')) {
				return response()->json($th->getMessage(), 400);
			}else{
				return redirect()->back()->withErrors($th->getMessage());
			}

		}
	}
}
