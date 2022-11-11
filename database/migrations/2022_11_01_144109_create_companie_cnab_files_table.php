<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateCompanieCnabFilesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('company_cnab_files', function (Blueprint $table) {
            $table->uuid('id')->primary();
			$table->uuid('company_id');
			$table->uuid('cnab_file_id');
            $table->timestamps();
			$table->softDeletes();

			$table->foreign('company_id')->references('id')->on('companies');
			$table->foreign('cnab_file_id')->references('id')->on('cnab_files');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('company_cnab_files');
    }
}
