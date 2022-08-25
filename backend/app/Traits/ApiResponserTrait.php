<?php 

namespace App\Traits;

trait ApiResponserTrait
{
    
    public function successResponse(array $data=[], int $code) : string
    {
        return response()->json(['data' => $data], $code);
    }

    public function errorResponse(string $message, int $code) : string
    {
        return response()->json(['error' => $message, 'code' => $code], $code);
    }
}