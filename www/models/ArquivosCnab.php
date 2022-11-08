<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "arquivos_cnab".
 *
 * @property int $id
 * @property int $tipo_transacao
 * @property string|null $data
 * @property float|null $valor
 * @property string|null $cpf
 * @property string|null $cartao
 * @property string|null $hora
 * @property string|null $dono_loja
 * @property string|null $nome_loja
 *
 * @property TiposDeTransacoes $tipoTransacao
 */
class ArquivosCnab extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'arquivos_cnab';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['tipo_transacao'], 'required'],
            [['tipo_transacao'], 'integer'],
            [['data'], 'safe'],
            [['valor'], 'number'],
            [['cpf', 'cartao'], 'string', 'max' => 11],
            [['hora'], 'string', 'max' => 6],
            [['dono_loja'], 'string', 'max' => 14],
            [['nome_loja'], 'string', 'max' => 19],
            [['tipo_transacao'], 'exist', 'skipOnError' => true, 'targetClass' => TiposDeTransacoes::className(), 'targetAttribute' => ['tipo_transacao' => 'tipo']],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'tipo_transacao' => 'Tipo Transacao',
            'data' => 'Data',
            'valor' => 'Valor',
            'cpf' => 'Cpf',
            'cartao' => 'Cartao',
            'hora' => 'Hora',
            'dono_loja' => 'Dono Loja',
            'nome_loja' => 'Nome Loja',
        ];
    }

    /**
     * Gets query for [[TipoTransacao]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getTipoTransacao()
    {
        return $this->hasOne(TiposDeTransacoes::className(), ['tipo' => 'tipo_transacao']);
    }
}
