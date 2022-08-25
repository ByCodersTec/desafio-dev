<?php

use Illuminate\Support\Facades\Route;

Route::get('transaction', [\App\Http\Controllers\Api\TransactionController::class,'index']);
Route::post('transaction/import', [\App\Http\Controllers\Api\TransactionController::class,'import']);