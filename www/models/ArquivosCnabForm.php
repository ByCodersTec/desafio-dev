<?php

namespace app\models;

use yii\base\Model;
use yii\web\UploadedFile;

class ArquivosCnabForm extends Model
{
    /**
     * @var UploadedFile
     */
    public $filecnab;
    public $date;
    
    public function rules()
    {
        return [
            // [['filecnab'], 'required'],
            ['filecnab', 'file', 'extensions' => ['txt']]
        ];
    }

    public function attributesLabels()
    {
        return [
            'nome' => 'Nome',
            'filecnab' => 'Arquivo'
        ];
    }

    public function parsearFileCNAB():array
    {
        #TODO: Adicionar funcionalidade para salvar arquivo enviado.
        $fileCNAB = UploadedFile::getInstance($this, 'filecnab');
        $fileCNABArray = [];

        $fileTemp = @fopen($fileCNAB->tempName, "r");
        #TODO: Adicionar validação para cada dado.
        while (!feof($fileTemp)) {
            $row = fgets($fileTemp);
            if(strlen($row) > 1){
                array_push($fileCNABArray,
                    [
                        'tipo_transacao' => substr($row, 0, 1),
                        'data' => substr($row, 1, 8),
                        'valor' => floatval(substr($row, 9, 10)) / 100,
                        'cpf' => substr($row, 19, 11),
                        'cartao' => substr($row, 30, 12),
                        'hora' => substr($row, 42, 6),
                        'dono_loja' => trim(substr($row, 48, 14)),
                        'nome_loja' => trim(substr($row, 62, 19))
                    ]
                );
                
            }
        }
        fclose($fileTemp);
        return $fileCNABArray;
    }

}