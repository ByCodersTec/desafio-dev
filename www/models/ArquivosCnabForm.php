<?php

namespace app\models;

use yii\base\Model;

class ArquivosCnabForm extends Model
{
    public $file;
    public $date;
    
    public function rules()
    {
        return [
            [['file', 'date'], 'required'],
            ['file', 'file']
        ];
    }

    public function attributesLabels()
    {
        return [
            'nome' => 'Nome',
            'file' => 'Arquivo'
        ];
    }

}