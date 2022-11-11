<?php

namespace App\Models\Traits;

use Ramsey\Uuid\Uuid;

trait TraitUuid
{
    /**
     * The primary key for the model.
     *
     * @var string
     */
    protected $primaryKey = 'id';


    public static function boot()
    {
        parent::boot();
        static::creating(function ($bj) {
            $bj->id = Uuid::uuid4();
        });
    }

    /**
     * getFillable
     *
     * @return array
     */
    public function getFillable(): array
    {
        return $this->fillable;
    }
}