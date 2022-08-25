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
            
            return Storage::putFile('uploads/imports', $file);

        } catch (\Throwable $th) {
            Log::error('TransactionService - uploadFile error: ' . $th->getMessage());
            throw $th;
        }
    }
}