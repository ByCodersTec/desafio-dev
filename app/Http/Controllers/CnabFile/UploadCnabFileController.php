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

	public function upload(ParseFileService $service,UploadCnabFileRequest $request)
	{
		try {
			return $service->execute($request->file('cnab_file'));
		} catch (\Throwable $th) {
			return response()->json($th->getMessage(), 400);
		}
	}
}
