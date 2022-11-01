<?php

namespace App\Models;

use App\Models\Traits\TraitUuid;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Company extends Model
{
	use HasFactory, TraitUuid, SoftDeletes;

	public $fillable = [
		'legal_person_name',
		'person_name'
	];
}
