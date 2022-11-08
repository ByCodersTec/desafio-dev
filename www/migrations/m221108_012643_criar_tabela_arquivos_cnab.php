<?php

use yii\db\Migration;

/**
 * Class m221108_012643_criar_tabela_arquivos_cnab
 */
class m221108_012643_criar_tabela_arquivos_cnab extends Migration
{
    /**
     * {@inheritdoc}
     */
    public function safeUp()
    {
        $this->createTable('arquivos_cnab', [
            'id' => $this->primaryKey(),
            'tipo_transacao' => $this->integer(1)->notNull(),
            'data' => $this->date(),
            'valor' => $this->float(2),
            'cpf' => $this->string(11),
            'cartao' => $this->string(11),
            'hora' => $this->string(6),
            'dono_loja' => $this->string(14),
            'nome_loja' => $this->string(19),
        ]);

        $this->addForeignKey(
            'tipo_fk', 'arquivos_cnab', 'tipo_transacao',
             'tipos_de_transacoes', 'tipo'
        );

    }

    /**
     * {@inheritdoc}
     */
    public function safeDown()
    {
        echo "m221108_012643_criar_tabela_arquivos_cnab cannot be reverted.\n";

        return false;
    }

    /*
    // Use up()/down() to run migration code without a transaction.
    public function up()
    {

    }

    public function down()
    {
        echo "m221108_012643_criar_tabela_arquivos_cnab cannot be reverted.\n";

        return false;
    }
    */
}
