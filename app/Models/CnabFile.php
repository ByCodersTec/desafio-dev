<?php

namespace App\Models;

use App\Models\Traits\TraitUuid;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class CnabFile extends Model
{
	use HasFactory, TraitUuid, SoftDeletes;

	public $fillable = [
		'transaction_date',
		'transaction_hour',
		'transaction_recipient_cpf',
		'transaction_card',
		'transaction_value'
	];
}
