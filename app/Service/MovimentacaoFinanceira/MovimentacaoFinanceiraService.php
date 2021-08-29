<?php

namespace App\Service\MovimentacaoFinanceira;

use App\Constants\Cnab;
use App\Models\MovimentacaoFinanceira;
use App\Models\TipoOperacao;
use Illuminate\Support\Facades\Log;

class MovimentacaoFinanceiraService
{
    /**
     * @var Cnab $cnab
     */
    private $cnab;

    /**
     * @var CnabConstants $cnab
     */
    public function __construct(Cnab $cnab)
    {
        $this->cnab = $cnab;
    }

    /**
     * @param string $linha
     */
    public function save($linha)
    {
        try {
            $tipo = substr($linha, $this->cnab::TIPO['inicio'] - 1, $this->cnab::TIPO['tamanho']);
            $data = substr($linha, $this->cnab::DATA['inicio'] - 1, $this->cnab::DATA['tamanho']);
            $valor = substr($linha, $this->cnab::VALOR['inicio'] - 1, $this->cnab::VALOR['tamanho']);
            $cpf = substr($linha, $this->cnab::CPF['inicio'] - 1, $this->cnab::CPF['tamanho']);
            $cartao = substr($linha, $this->cnab::CARTAO['inicio'] - 1, $this->cnab::CARTAO['tamanho']);
            $hora = substr($linha, $this->cnab::HORA['inicio'] - 1, $this->cnab::HORA['tamanho']);
            $dono_loja = substr($linha, $this->cnab::DONO_LOJA['inicio'] - 1, $this->cnab::DONO_LOJA['tamanho']);
            $nome_loja = substr($linha, $this->cnab::NOME_LOJA['inicio'] - 1, $this->cnab::NOME_LOJA['tamanho']);

            return MovimentacaoFinanceira::create([
                'tipo_transacao' => $tipo,
                'data' => $data,
                'valor' => $valor,
                'cpf' => $cpf,
                'cartao' => $cartao,
                'hora' => $hora,
                'dono_da_loja' => $dono_loja,
                'nome_da_loja' => $nome_loja
            ]);
        } catch (\Throwable $th) {
            Log::error($th);
        }
    }
}
