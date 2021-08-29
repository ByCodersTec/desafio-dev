<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class TipoOperacao extends Model
{
    use HasFactory;

     /**
     * @var array $fillable
     */
    protected $fillable =  [
        'tipo',
        'descricao',
        'natureza',
        'sinal'
    ];

    /**
     * @var array $dates
     */
    protected $dates = [
        'created_at',
        'updated_at'
    ];
}
