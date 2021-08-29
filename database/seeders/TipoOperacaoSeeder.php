<?php

namespace Database\Seeders;

use App\Models\TipoOperacao;
use Illuminate\Database\Seeder;

class TipoOperacaoSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        TipoOperacao::create([
            'tipo' => 1,
            'descricao' => 'Débito',
            'natureza' => 'Entrada',
            'sinal' => '+'
        ]);

        TipoOperacao::create([
            'tipo' => 2,
            'descricao' => 'Boleto',
            'natureza' => 'Saída',
            'sinal' => '-'
        ]);

        TipoOperacao::create([
            'tipo' => 3,
            'descricao' => 'Financiamento',
            'natureza' => 'Saída',
            'sinal' => '-'
        ]);

        TipoOperacao::create([
            'tipo' => 4,
            'descricao' => 'Crédito',
            'natureza' => 'Entrada',
            'sinal' => '+'
        ]);

        TipoOperacao::create([
            'tipo' => 5,
            'descricao' => 'Recebimento Empréstimo',
            'natureza' => 'Entrada',
            'sinal' => '+'
        ]);

        TipoOperacao::create([
            'tipo' => 6,
            'descricao' => 'Vendas',
            'natureza' => 'Entrada',
            'sinal' => '+'
        ]);

        TipoOperacao::create([
            'tipo' => 7,
            'descricao' => 'Recebimento TED',
            'natureza' => 'Entrada',
            'sinal' => '+'
        ]);

        TipoOperacao::create([
            'tipo' => 8,
            'descricao' => 'Recebimento DOC',
            'natureza' => 'Entrada',
            'sinal' => '+'
        ]);

        TipoOperacao::create([
            'tipo' => 9,
            'descricao' => 'Aluguel',
            'natureza' => 'Saída',
            'sinal' => '-'
        ]);
        
    }
}
