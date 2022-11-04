<?php

namespace App\Http\Resources\Companies;

use App\Http\Resources\CnabFiles\CnabFilesResource;
use Illuminate\Http\Resources\Json\JsonResource;

class CompaniesResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return array|\Illuminate\Contracts\Support\Arrayable|\JsonSerializable
     */
    public function toArray($request)
    {
        return [
			"id" => $this->id,
			"legal_person_name" => $this->legal_person_name,
			"person_name" => $this->person_name,
			"person_cpf" => $this->person_cpf,
			"account_balance" => round($this->account_balance,2),
			"importations_cnab_files" => CnabFilesResource::collection($this->cnabFiles)
		];
    }
}
