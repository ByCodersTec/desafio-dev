<?php

namespace App\Models;

use App\Models\Traits\TraitUuid;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class CnabFile extends Model
{
	use HasFactory, TraitUuid, SoftDeletes;

	public $incrementing = false;

    protected $keyType = 'string';

	public $timestamps = true;

	public $fillable = [
		'transaction_date',
		'transaction_value',
		'transaction_card',
		'transaction_hour',
		'type_id'
	];

	public function companies()
	{
		return $this->belongsToMany(Company::class,'company_cnab_files','cnab_file_id','company_id')
		->using(CompanyCnabFile::class)
		->withTimestamps();
	}

	public function typeTransaction()
	{
		return $this->belongsTo(TypeTransaction::class,'type_id','id');
	}
}
