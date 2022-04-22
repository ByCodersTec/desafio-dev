import {
  Body,
  Controller,
  Get,
  Post,
  UploadedFile,
  UseInterceptors,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import { Transaction, TransactionType } from '@prisma/client';
import { TransactionsService } from 'src/services/transactions/transactions.service';

@Controller('transactions')
export class TransactionsController {
  constructor(private readonly transactionsService: TransactionsService) {}

  @Get('')
  async getTransactions(): Promise<Transaction[]> {
    return this.transactionsService.transactions();
  }

  @Get('types')
  async getTransactionsTypes(): Promise<TransactionType[]> {
    return this.transactionsService.transactionsTypes();
  }

  @Post('Upload')
  @UseInterceptors(FileInterceptor('file'))
  async uploadTransactionsFile(
    @UploadedFile() file: Express.Multer.File,
  ): Promise<any> {
    return this.transactionsService.processCNABFile(file);
  }

}
