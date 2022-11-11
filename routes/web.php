<?php

use App\Http\Controllers\CnabFile\UploadCnabFileController;
use App\Http\Controllers\Company\IndexCompanyWithCnabFileController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

// Route::get('/', function () {
//     return view('welcome');
// });

Route::get('/create',[UploadCnabFileController::class,'uploadForm']);
Route::post('/upload/file-form',[UploadCnabFileController::class,'upload'])->name('upload.cnab.file.web');

Route::get('/list',[IndexCompanyWithCnabFileController::class,'indexView'])->name("index.companies");
