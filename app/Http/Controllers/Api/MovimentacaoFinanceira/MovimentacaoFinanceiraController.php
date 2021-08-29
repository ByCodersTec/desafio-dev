<?php

namespace App\Http\Controllers\Api\MovimentacaoFinanceira;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Models\MovimentacaoFinanceira;
use Illuminate\Support\Facades\Storage;
use App\Service\MovimentacaoFinanceira\MovimentacaoFinanceiraService;

class MovimentacaoFinanceiraController extends Controller
{
    /**
     * @var string $path
     */
    private $path = 'public/movimentacao_financeira';

    /**
     * @var MovimentacaoFinanceiraService $service
     */
    private $service;

    /**
     * 
     */
    public function __construct(MovimentacaoFinanceiraService $service)
    {
        $this->service = $service;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        try {
            $movimentacao_financeira = MovimentacaoFinanceira::with('tipoOperacao')->orderBy('data')->get();

            return response()->json(['movimentacao_financeira' => $movimentacao_financeira]);
        } catch (\Throwable $th) {
            return response()->json(['error' => $th->getMessage()], 400);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        try {
            $movimentacao_financeira = [];
            $file = $request->file('cnab');
            $url = Storage::putFileAs($this->path, $file, 'cnab.txt');
            Storage::setVisibility($url, 'public');
            $cnab = file(str_replace('public', 'storage', $url));

            foreach ($cnab as $line) {
                array_push($movimentacao_financeira, $this->service->save($line));
            }

            return response()->json(['movimentacao_financeira' => $movimentacao_financeira]);
        } catch (\Throwable $th) {
            return response()->json(['error' => $th->getMessage()], 400);
        }
    }
}
