<?php

namespace App\Console\Commands;

use Illuminate\Console\Command;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Artisan;

class RefreshPassword extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'refresh:passport';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Command description';

    /**
     * Create a new command instance.
     *
     * @return void
     */
    public function __construct()
    {
        parent::__construct();
    }

    /**
     * Execute the console command.
     *
     * @return int
     */
    public function handle()
    {

        Artisan::call('passport:keys');
        DB::table('oauth_clients')->updateOrInsert(
            [
                'id' => 1,
            ],
            [
                'name' => 'Coders passport',
                'secret' => '3415f8a09480dbc8af2eefacf3347469eb5e3ec3d343085ae514bec7b33c5dcb80de0dcb',
                'provider' => 'users',
                'redirect' => 'http://localhost',
                'personal_access_client' => 0,
                'password_client' => 1,
                'revoked' => 0,
            ]
        );
    }
}
