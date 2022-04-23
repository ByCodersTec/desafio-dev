import { Module } from '@nestjs/common';
import { DatabaseModule } from 'src/database/database.module';
import { TransactionsService } from 'src/services/transactions/transactions.service';
import { TransactionsController } from './transactions/transactions.controller';
import { StoresController } from './stores/stores.controller';
import { StoresService } from 'src/services/stores/stores.service';

@Module({
  imports: [DatabaseModule],
  controllers: [TransactionsController, StoresController],
  providers: [TransactionsService, StoresService],
})
export class HttpModule {}
