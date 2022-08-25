<?php 

namespace App\Traits;

use Illuminate\Http\Response;

trait ApiResponserTrait
{
    
    public function successResponse($data=[], $code = Response::HTTP_OK)
    {
        return response()->json(['data' => $data], $code);
    }

    public function errorResponse(string $message, int $code)
    {
        return response()->json(['error' => $message, 'code' => $code], $code);
    }
}