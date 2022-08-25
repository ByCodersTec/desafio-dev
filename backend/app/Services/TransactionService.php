<?php

namespace App\Services;

use Illuminate\Http\UploadedFile;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Storage;

class TransactionService
{

    /**
     * Patch File to Upload
     *
     * @var string
     */
    protected $pathFileUpload = 'uploads/imports';

    /**
     * Process upload file
     *
     * @param UploadedFile $file
     * @return string
     */
    public function uploadFile(UploadedFile $file): string
    {
        try {
            
            return Storage::putFile($this->pathFileUpload, $file);

        } catch (\Throwable $th) {
            Log::error('TransactionService - uploadFile error: ' . $th->getMessage());
            throw $th;
        }
    }

    public function readFile(string $pathFile)
    {
        try {
            
            $data = null;
            $fp = fopen(Storage::path($pathFile), "r");

            while (!feof($fp)) {
                $data[] = fgets($fp);
            }

            fclose($fp);
            return $data;

        } catch (\Throwable $th) {
            Log::error('TransactionService - readFile error: ' . $th->getMessage());
            throw $th;
        }
    }
}