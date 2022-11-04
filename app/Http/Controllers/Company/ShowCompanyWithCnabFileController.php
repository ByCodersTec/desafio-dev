<?php

namespace App\Http\Controllers\Company;

use App\Http\Controllers\Controller;
use App\Http\Resources\Companies\CompaniesResource;
use App\Http\Services\Company\ShowCompanyWithCnabFileService;
use Illuminate\Http\Request;

class ShowCompanyWithCnabFileController extends Controller
{
	public function show(ShowCompanyWithCnabFileService $service, Request $request)
	{
		try {
			return new CompaniesResource($service->execute($request->company_id));
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}
}
