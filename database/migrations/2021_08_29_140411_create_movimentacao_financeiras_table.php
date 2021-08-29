<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateMovimentacaoFinanceirasTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('movimentacao_financeiras', function (Blueprint $table) {
            $table->id();
            $table->string('tipo_transacao');
            $table->date('data');
            $table->double('valor', 8, 2);
            $table->string('cpf');
            $table->string('cartao');
            $table->string('hora');
            $table->string('dono_da_loja');
            $table->string('nome_da_loja');
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
        Schema::dropIfExists('movimentacao_financeiras');
    }
}
