<?php

use yii\db\Migration;

/**
 * Class m221108_011330_criar_tabela_tipos_de_trancacoes
 */
class m221108_011330_criar_tabela_tipos_de_trancacoes extends Migration
{
    /**
     * {@inheritdoc}
     */
    public function safeUp()
    {
        $this->createTable('tipos_de_transacoes', [
            'tipo' => $this->primaryKey(),
            'descricao' => $this->string()->notNull(),
            'natureza' => $this->string()->notNull(),
            'sinal' => $this->boolean()->notNull(),
        ]);
        $this->batchInsert('tipos_de_transacoes',
        ['descricao',               'natureza', 'sinal'],
            [
                ['Débito',                  'Entrada',  true],
                ['Boleto',	                'Saída',    false],
                ['Financiamento',	        'Saída',    false],
                ['Crédito',	                'Entrada',  true],
                ['Recebimento Empréstimo',  'Entrada',  true],
                ['Vendas',	                'Entrada',  true],
                ['Recebimento TED',	        'Entrada',  true],
                ['Recebimento DOC',	        'Entrada',  true],
                ['Aluguel',	                'Saída',    false]
            ]
        );
    }

    /**
     * {@inheritdoc}
     */
    public function safeDown()
    {
        echo "m221108_011330_criar_tabela_tipos_de_trancacoes cannot be reverted.\n";

        return false;
    }

    /*
    // Use up()/down() to run migration code without a transaction.
    public function up()
    {

    }

    public function down()
    {
        echo "m221108_011330_criar_tabela_tipos_de_trancacoes cannot be reverted.\n";

        return false;
    }
    */
}
