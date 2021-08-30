<?php

namespace App\Http\Controllers\Web;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Log;
use App\Http\Controllers\Controller;

class WelcomeController extends Controller
{
    public function index()
    {
        try {
            return view('welcome');
        } catch (\Exception $exception) {
            Log::error($exception);
            abort(500, $exception);
        }
    }
}
