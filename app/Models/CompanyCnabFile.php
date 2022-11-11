<?php

namespace App\Models;

use App\Models\Traits\TraitUuid;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\Pivot;
use Illuminate\Database\Eloquent\SoftDeletes;

class CompanyCnabFile extends Pivot
{
	use HasFactory, TraitUuid, SoftDeletes;

	public $incrementing = false;

    protected $keyType = 'string';

	public $timestamps = true;

	public $fillable = [
		'company_id',
		'cnab_file_id'
	];
}
