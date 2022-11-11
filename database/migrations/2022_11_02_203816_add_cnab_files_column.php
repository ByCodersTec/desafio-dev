<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddCnabFilesColumn extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('cnab_files',function(Blueprint $table){
			$table->uuid('type_id')->after('id');

			$table->foreign('type_id')->references('id')->on('type_transactions');
		});
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
		Schema::table('cnab_files',function(Blueprint $table){
			$table->dropForeign('type_id');
			$table->dropColumn('type_id');
		});
    }
}
