<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;

class Transaction extends Model
{
    use HasFactory, SoftDeletes;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'import_history_id',
        'type_transaction_id',
        'date',
        'value',
        'cpf',
        'card',
        'hour',
        'store_owner',
        'store_name',
    ];

    /**
     * Find a Import History
     *
     */
    public function importHistory()
    {
        return $this->belongsTo(\App\Models\ImportHistory::class, 'import_history_id');
    }

    /**
     * Find a Type Transaction
     *
     */
    public function typeTransaction()
    {
        return $this->belongsTo(\App\Models\TypeTransaction::class, 'type_transaction_id');
    }


}
