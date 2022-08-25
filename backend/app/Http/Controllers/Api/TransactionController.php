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

class TransactionController extends Controller
{
    use ApiResponserTrait;

    protected TransactionService $transactionService;

    public function __construct()
    {
        $this->transactionService = new TransactionService();
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $transactions = Transaction::with('typeTransaction')->get();
        return $this->successResponse($transactions);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

    /**
     * Import the file and save its data in storage
     *
     * @param Request $request
     * @return void
     */
    public function import(Request $request)
    {
        try {

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
            return $this->errorResponse($th->getMessage(), Response::HTTP_INTERNAL_SERVER_ERROR);
        }
    }
}
