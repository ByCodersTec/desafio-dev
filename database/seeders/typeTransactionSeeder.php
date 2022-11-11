<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Ramsey\Uuid\Uuid;

class typeTransactionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'debit',
            'operation_type' => 'input',
            'transaction_code' => '1',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'bill',
            'operation_type' => 'output',
            'transaction_code' => '2',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'financing',
            'operation_type' => 'output',
            'transaction_code' => '3',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'credit',
            'operation_type' => 'input',
            'transaction_code' => '4',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'loan_receipt',
            'operation_type' => 'input',
            'transaction_code' => '5',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'sale',
            'operation_type' => 'input',
            'transaction_code' => '6',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'TED',
            'operation_type' => 'input',
            'transaction_code' => '7',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4(),
            'transaction_name' => 'DOC',
            'operation_type' => 'input',
            'transaction_code' => '8',
        ]);
        DB::table('type_transactions')->insert([
			'id' => Uuid::uuid4()->toString(),
            'transaction_name' => 'rent',
            'operation_type' => 'output',
            'transaction_code' => '9',
        ]);
    }
}
