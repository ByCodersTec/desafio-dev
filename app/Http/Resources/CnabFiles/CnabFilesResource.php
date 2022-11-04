<?php

namespace App\Http\Resources\CnabFiles;

use Illuminate\Http\Resources\Json\JsonResource;

class CnabFilesResource extends JsonResource
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
				"type_id" => $this->type_id,
				"type_name" => $this->typeTransaction->transaction_name,
				"type_operation" => $this->typeTransaction->operation_type,
				"transaction_date" => $this->transaction_date,
				"transaction_value" => round($this->transaction_value,2),
				"transaction_card" => $this->transaction_card,
				"transaction_hour" => $this->transaction_hour
		];
    }
}
