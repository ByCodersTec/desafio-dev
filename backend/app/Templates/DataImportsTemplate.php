<?php 

namespace App\Templates;

class DataImportsTemplate
{
    protected $importType;
    protected $template = [
        'CNAB' => [
            'type_transaction_id' => [
                'start'     => 1,
                'length'    => 1
            ],
            'date' => [
                'start'     => 2,
                'length'    => 8,
                'normatize' => true
            ],
            'value' => [
                'start'     => 10,
                'length'    => 10,
                'normatize' => true
            ],
            'cpf' => [
                'start'     => 20,
                'length'    => 11
            ],
            'card' => [
                'start'     => 31,
                'length'    => 12
            ],
            'hour' => [
                'start'     => 43,
                'length'    => 6,
                'normatize' => true
            ],
            'store_owner' => [
                'start'     => 49,
                'length'    => 14,
                'normatize' => true
            ],
            'store_name' => [
                'start'     => 63,
                'length'    => 19,
                'normatize' => true
            ],
        ]
    ];

    public function __construct(string $type)
    {
        $this->importType = $type;
    }

    public function getField($field, $row)
    {
        //busca as posições dos campos no template
        $start = $this->template[$this->importType][$field]['start'];
        $length = $this->template[$this->importType][$field]['length'];
        //recupera a informação 
        $data = substr($row, $start-1, $length);

        //verifica se o dado precisa de tratamento
        if (isset($this->template[$this->importType][$field]['normatize']) && $this->template[$this->importType][$field]['normatize']) {
            $data = $this->normatizeData($field, $data);
        }

        return $data;
    }

    public function normatizeData(string $field, string $data)
    {
        
        switch ($field) {
            case 'value':
                $normatized = substr($data, 0 ,-2).".".substr($data, -2);
                $normatized = (float) $normatized;
                break;
            case 'date':
                $normatized = date("Y-m-d", strtotime($data));
                break;
            case 'hour':
                $normatized = date("H:i:s", strtotime($data));
                break;
            case 'store_owner':
                $normatized = trim($data);
                break;
            case 'store_name':
                $normatized = trim($data);
                break;
            default:
                $normatized = $data;
                break;
        }

        return $normatized;
    }


}