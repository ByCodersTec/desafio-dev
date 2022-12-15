<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "tipos_de_transacoes".
 *
 * @property int $tipo
 * @property string $descricao
 * @property string $natureza
 * @property int $sinal
 *
 * @property ArquivosCnab[] $arquivosCnabs
 */
class TiposDeTransacoes extends \yii\db\ActiveRecord
{
    /**
     * {@inheritdoc}
     */
    public static function tableName()
    {
        return 'tipos_de_transacoes';
    }

    /**
     * {@inheritdoc}
     */
    public function rules()
    {
        return [
            [['descricao', 'natureza', 'sinal'], 'required'],
            [['sinal'], 'integer'],
            [['descricao', 'natureza'], 'string', 'max' => 255],
        ];
    }

    /**
     * {@inheritdoc}
     */
    public function attributeLabels()
    {
        return [
            'tipo' => 'Tipo',
            'descricao' => 'Descricao',
            'natureza' => 'Natureza',
            'sinal' => 'Sinal',
        ];
    }

    /**
     * Gets query for [[ArquivosCnabs]].
     *
     * @return \yii\db\ActiveQuery
     */
    public function getArquivosCnabs()
    {
        return $this->hasMany(ArquivosCnab::className(), ['tipo_transacao' => 'tipo']);
    }
}
