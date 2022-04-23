import {
  ClassSerializerInterceptor,
  Controller,
  Get,
  Param,
  ParseIntPipe,
  UseInterceptors,
} from '@nestjs/common';
import { StoresService } from 'src/services/stores/stores.service';
import { TransactionsService } from 'src/services/transactions/transactions.service';
import { PlainToTransactionInstanceInterceptor } from '../transactions/plain-to-transaction-instance.interceptor';

@Controller('stores')
export class StoresController {
  constructor(
    private readonly storesService: StoresService,
    private readonly transactionsService: TransactionsService,
  ) {}

  @Get('/')
  async getStores() {
    return this.storesService.stores();
  }

  @Get('/:id')
  async getStore(@Param('id', ParseIntPipe) id: number) {
    return this.storesService.store({ id });
  }

  @Get(':id/transactions')
  @UseInterceptors(PlainToTransactionInstanceInterceptor)
  @UseInterceptors(ClassSerializerInterceptor)
  async getStoreTransactions(@Param('id', ParseIntPipe) id: number) {
    return this.transactionsService.transactions({ where: { storeId: id } });
  }
}
