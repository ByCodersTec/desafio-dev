<?php

use Illuminate\Support\Facades\Route;

Route::get('summary', [\App\Http\Controllers\Api\TransactionController::class,'summary']);
Route::get('transaction', [\App\Http\Controllers\Api\TransactionController::class,'index']);
Route::post('transaction/import', [\App\Http\Controllers\Api\TransactionController::class,'import']);