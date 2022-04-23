import { Module } from '@nestjs/common';
import { DatabaseModule } from 'src/database/database.module';
import { TransactionsService } from 'src/services/transactions/transactions.service';
import { TransactionsController } from './transactions/transactions.controller';

@Module({
  imports: [DatabaseModule],
  controllers: [TransactionsController],
  providers: [TransactionsService],
})
export class HttpModule {}
