<?php

namespace App\Providers\CnabFile;

use App\Repositories\CnabFile\CnabFileRepository;
use App\Repositories\Interfaces\CnabFile\CnabFileRepositoryInterface;
use Illuminate\Support\ServiceProvider;

class CnabFileProvider extends ServiceProvider
{
    /**
     * Register services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton(
            CnabFileRepositoryInterface::class,
            CnabFileRepository::class
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
