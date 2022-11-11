<?php

namespace App\Http\Requests\CnabFile;

use Illuminate\Foundation\Http\FormRequest;

class UploadCnabFileRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'cnab_file' => 'required|mimetypes:text/plain|file'
        ];
    }

	public function messages()
	{
		return [
			'cnab_file.required' => 'Arquivo não encontrado (obrigatório)',
			'cnab_file.mimetypes' => 'Só é permitido upload de arquivo no formato .txt',
			'cnab_file.file' => 'Só é permitido envio no formato de arquivo'
		];
	}
}
