<?php

use App\Http\Controllers\CnabFile\UploadCnabFileController;
use App\Http\Controllers\Company\IndexCompanyWithCnabFileController;
use App\Http\Controllers\Company\ShowCompanyWithCnabFileController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

// Route::middleware('auth:api')->get('/user', function (Request $request) {
//     return $request->user();
// });

Route::post('/upload/file',[UploadCnabFileController::class,'upload']);

Route::get('/index/companies',[IndexCompanyWithCnabFileController::class,'index']);
Route::get('/show/companies',[ShowCompanyWithCnabFileController::class,'show']);
