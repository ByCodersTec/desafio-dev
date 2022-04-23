import {
  CallHandler,
  ExecutionContext,
  Injectable,
  NestInterceptor,
} from '@nestjs/common';
import { Transaction } from '@prisma/client';
import { plainToInstance } from 'class-transformer';
import { Observable, map } from 'rxjs';
import { TransactionEntity } from './transaction-entity';

@Injectable()
export class PlainToTransactionInstanceInterceptor implements NestInterceptor {
  intercept(_context: ExecutionContext, next: CallHandler): Observable<any> {
    return next
      .handle()
      .pipe(
        map((transactions) =>
          transactions.map((transaction: Transaction) =>
            plainToInstance(TransactionEntity, transaction),
          ),
        ),
      );
  }
}
