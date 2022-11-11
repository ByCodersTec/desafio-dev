<?php

namespace App\Providers\Company;

use App\Repositories\Company\CompanyRepository;
use App\Repositories\Interfaces\Company\CompanyRepositoryInterface;
use Illuminate\Support\ServiceProvider;

class CompanyProvider extends ServiceProvider
{
    /**
     * Register services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton(
			CompanyRepositoryInterface::class,
			CompanyRepository::class
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
