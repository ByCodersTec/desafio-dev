<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateTransactionsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('transactions', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('import_history_id');
            $table->foreign('import_history_id')->references('id')->on('import_histories');
            $table->unsignedBigInteger('type_transaction_id');
            $table->foreign('type_transaction_id')->references('id')->on('type_transactions');
            $table->date('date');
            $table->double('value', 13, 2);
            $table->string('cpf');
            $table->string('card');
            $table->time('hour');
            $table->string('store_owner');
            $table->string('store_name');
            $table->softDeletes();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('transactions');
    }
}
