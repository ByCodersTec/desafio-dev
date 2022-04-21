import { Controller, Get } from '@nestjs/common';
import { TransactionType } from '@prisma/client';
import { TransactionsService } from 'src/services/transactions/transactions.service';

@Controller('transactions')
export class TransactionsController {
    constructor(private readonly transactionsService: TransactionsService) {

    }

    @Get('types')
    async getTransactionsTypes(): Promise<TransactionType[]> {
        return this.transactionsService.transactionsTypes()
    }
}
