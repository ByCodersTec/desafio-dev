<?php

namespace App\Models;

use App\Models\Traits\TraitUuid;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class TypeTransaction extends Model
{
    use HasFactory, TraitUuid, SoftDeletes;

	public $incrementing = false;

    protected $keyType = 'string';

	public $timestamps = true;

	public $fillable = [
		"transaction_name",
		"operation_type",
		"transaction_code"
	];
}
