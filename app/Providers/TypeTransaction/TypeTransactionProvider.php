<?php

namespace App\Providers\TypeTransaction;

use App\Repositories\Interfaces\TypeTransaction\TypeTransactionRepositoryInterface;
use App\Repositories\TypeTransaction\TypeTransactionRepository;
use Illuminate\Support\ServiceProvider;

class TypeTransactionProvider extends ServiceProvider
{
    /**
     * Register services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton(
            TypeTransactionRepositoryInterface::class,
            TypeTransactionRepository::class
        );
    }

    /**
     * Bootstrap services.
     *
     * @return void
     */
    public function boot()
    {
        //
    }
}
