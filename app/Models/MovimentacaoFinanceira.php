<?php

namespace App\Models;

use Carbon\Carbon;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class MovimentacaoFinanceira extends Model
{
    use HasFactory;

    /**
     * @var array $fillable
     */
    protected $fillable =  [
        'tipo_transacao',
        'data',
        'valor',
        'cpf',
        'cartao',
        'hora',
        'dono_da_loja',
        'nome_da_loja'
    ];

    /**
     * @var array $dates
     */
    protected $dates = [
        'created_at',
        'updated_at'
    ];

    //======================================================================================================
    // RELATIONSHIPS
    //======================================================================================================
    public function tipoOperacao()
    {
        return $this->hasOne(TipoOperacao::class, 'id', 'tipo_transacao');
    }

    //========================================================================================================
    // GETTERS E SETTERS
    //========================================================================================================
    public function getHoraAttribute($value)
    {
        $hora = substr_replace($value, ':', 2, 0);

        return substr_replace($hora, ':', 5, 0);
    }

    public function getDataAttribute($value)
    {
        return (new Carbon($value))->format('d-m-Y');
    }

    public function setValorAttribute($valor)
    {
        return $this->attributes['valor'] = $valor / 100;
    }
}
