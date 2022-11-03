<?php

namespace App\Models;

use App\Models\Traits\TraitUuid;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Company extends Model
{
	use HasFactory, TraitUuid, SoftDeletes;

	public $incrementing = false;

    protected $keyType = 'string';

	public $timestamps = true;

	public $fillable = [
		'legal_person_name',
		'person_name',
		'person_cpf'
	];

	public function CnabFile()
	{
		return $this->belongsToMany(CnabFile::class,'company_cnab_files')
		->using(CompanyCnabFile::class)
		->withTimestamps();
	}
}
