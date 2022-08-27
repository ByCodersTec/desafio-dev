<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;

/**
 * @OA\Info(
 *     title="API para Importação de Arquivo CNAB", 
 *     version="0.1",
 *     description="A API tem o objetivo de possiblitar a importação de arquivo CNAB com os dados das movimentações finanaceira de várias lojas e exibir os lançamentos",
 * ),
 */
class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;
}
