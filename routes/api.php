<?php

use App\Http\Controllers\Api\MovimentacaoFinanceira\MovimentacaoFinanceiraController;
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
Route::middleware('auth:api')->group(function () {
    Route::apiResource('movimentacao_financeira', MovimentacaoFinanceiraController::class)->only(['index', 'store']);
});