<?php

namespace App\Http\Controllers\Company;

use App\Http\Controllers\Controller;
use App\Http\Resources\Companies\CompaniesResource;
use App\Http\Services\Company\IndexCompanyWithCnabFileService;

class IndexCompanyWithCnabFileController extends Controller
{
	public function index(IndexCompanyWithCnabFileService $service)
	{
		try {
			return CompaniesResource::collection($service->execute());
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}
}
