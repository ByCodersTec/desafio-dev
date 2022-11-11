<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateCnabFilesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('cnab_files', function (Blueprint $table) {
            $table->uuid('id')->primary();
			$table->date('transaction_date');
			$table->double('transaction_value',12,2);
			$table->string('transaction_card',15);
			$table->time('transaction_hour');
            $table->timestamps();
			$table->softDeletes();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('cnab_files');
    }
}
