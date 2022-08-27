<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\ImportHistory;
use App\Models\Transaction;
use App\Services\TransactionService;
use App\Traits\ApiResponserTrait;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;
use Illuminate\Support\Facades\Validator;

class TransactionController extends Controller
{
    use ApiResponserTrait;

    protected TransactionService $transactionService;

    public function __construct()
    {
        $this->transactionService = new TransactionService();
    }

    /**
     * @OA\Get(
     *     path="/api/transaction",
     *     tags={"Transações"},
     *     summary="Lista as Transações",
     *     description="Endpoint destinado a listagem das Transações",
     *     operationId="listTransacoes",
     *     
     *     @OA\Response(
     *         response="200", 
     *         description="Lista de transações",
     *         @OA\MediaType(mediaType="application/json"),
     *     ),
     * )
     */
    public function index()
    {
        $transactions = Transaction::with('typeTransaction')->get();
        return $this->successResponse($transactions);
    }

    /**
     * @OA\Get(
     *     path="/api/summary",
     *     tags={"Transações"},
     *     summary="Lista as Transações",
     *     description="Endpoint destinado a prover informações gerais de dados importados",
     *     operationId="summaryTransacoes",
     *     
     *     @OA\Response(
     *         response="200", 
     *         description="Agrupamento de infomrações já importadas",
     *          @OA\JsonContent(
    *             @OA\Examples(example="result", value={"data": {"totImports": 4, "totTransactions": 64, "totStores": 6, "totCredit": 6387.11, "totDebit": 24906, "totBalance": -18518.89}}, summary="Resultado será um objeto")
    *         )
     *     ),
     * )
     */
    public function summary()
    {   
        try {
            
            return $this->successResponse($this->transactionService->getSummary());
            
        } catch (\Throwable $th) {
            Log::error("TransactionController - summary - " . $th->getMessage());
            return $this->errorResponse($th->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }

    public function storeTransactions(Request $request)
    {
        try {
            
            return $this->successResponse($this->transactionService->getStoreTransactions($request));

        } catch (\Throwable $th) {
            Log::error("TransactionController - transactionStores - " . $th->getMessage());
            return $this->errorResponse($th->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }

    public function stores()
    {
        try {
            
            return $this->successResponse($this->transactionService->getStores());

        } catch (\Throwable $th) {
            Log::error("TransactionController - transactionStores - " . $th->getMessage());
            return $this->errorResponse($th->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * @OA\Post(
     *     path="/api/transaction/import",
     *     tags={"Transações"},
     *     summary="Importação das Transações",
     *     description="Endpoint destinado a importação do arquivo CNAB com as transações das lojas",
     *     operationId="importTransacoes",
     *     @OA\MediaType(mediaType="multipart/form-data"),
     *     @OA\Response(
     *         response="200", 
     *         description="Importação efetuada com sucesso",
     *         @OA\MediaType(mediaType="application/json"),
     *     ),
     *     @OA\Response(
     *         response="422", 
     *         description="Parametros esperados não foram encontrados",
     *         @OA\MediaType(mediaType="application/json"),
     *     ),
     *      @OA\RequestBody(
     *          @OA\MediaType(
     *              mediaType="multipart/form-data",
     *              @OA\Schema(
     *                  type="object",
     *                  @OA\Property(
     *                     description="arquivo CNAB",
     *                     property="file",
     *                  ),
     *              ),
     *           ),
     *      ),
     * )
     */
    public function import(Request $request)
    {
        try {
            
            $messages  = [
                'file.required' => 'O arquivo é obrigatório',
            ];
    
            $validator = Validator::make($request->all(), [
                'file' => 'required',
            ], $messages);
    
            if ($validator->fails()) 
                return $this->errorResponse(['type'=>'validator', 'messages'=>$validator->errors()], Response::HTTP_UNPROCESSABLE_ENTITY);

            DB::beginTransaction();
            
            $path = $this->transactionService->uploadFile($request->file('file'));

            $importHistory = new ImportHistory();
            $importHistory->date = date("Y-m-d H:i:s");
            $importHistory->file = $path;
            $importHistory->status = 'importado';
            $importHistory->save();

            $linesData = $this->transactionService->readFile($path);
            $transactions = $this->transactionService->parserData($linesData, $importHistory->id);

            Transaction::insert($transactions);
            
            DB::commit();

            $return = [
                'transaction_history_id'    => $importHistory->id,
                'transactions_imported'     => count($transactions)
            ];

            return $this->successResponse($return);

        } catch (\Throwable $th) {
            DB::rollback();
            Log::error("TransactionController - import - " . $th->getMessage());
            return $this->errorResponse($th->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }
}
