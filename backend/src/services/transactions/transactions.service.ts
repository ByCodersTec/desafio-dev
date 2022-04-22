import { Injectable } from '@nestjs/common';
import { Prisma, Transaction } from '@prisma/client';
import { addHours, parse } from 'date-fns';
import { PrismaService } from 'src/database/prisma/prisma.service';
import { CNAB_TRANSACTIONS_SPEC, RAW_TRANSACTIONS_TIME_OFFSET } from './transactions.service.constants';

@Injectable()
export class TransactionsService {
  constructor(private prisma: PrismaService) { }

  async transactionsTypes(params?: {
    skip?: number;
    take?: number;
    cursor?: Prisma.TransactionTypeWhereUniqueInput;
    where?: Prisma.TransactionTypeWhereInput;
  }) {
    return this.prisma.transactionType.findMany({ ...params });
  }

  async processTransactionsFile(file: Express.Multer.File): Promise<any> {
    const rawTransactions = file.buffer.toString().split(/(?:\r\n|\r|\n)/g);

    const parsedTransactions = Promise.all(
      rawTransactions.flatMap<Promise<Transaction | Transaction[]>>(
        async (rawTransaction) => {
          if (!rawTransaction) return [];

          const transactionTypeId = Number(rawTransaction[CNAB_TRANSACTIONS_SPEC.TYPE]);

          const rawDate = rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.DATE_START, CNAB_TRANSACTIONS_SPEC.DATE_END);
          const rawTime = rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.TIME_START, CNAB_TRANSACTIONS_SPEC.TIME_END);

          const rawDateTime = `${rawDate}${rawTime}`;
          const parsedDate = parse(rawDateTime, 'yyyyMMddHHmmss', new Date());
          const dateOffsetAdjusted = addHours(
            parsedDate,
            RAW_TRANSACTIONS_TIME_OFFSET,
          );

          const value = Number(rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.VALUE_START, CNAB_TRANSACTIONS_SPEC.VALUE_END));
          const CPF = rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.CPF_START, CNAB_TRANSACTIONS_SPEC.CPF_END);
          const card = rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.CARD_START, CNAB_TRANSACTIONS_SPEC.CARD_END);
          const storeOwner = rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.STORE_OWNER_START, CNAB_TRANSACTIONS_SPEC.STORE_OWNER_END).trim();
          const storeName = rawTransaction.slice(CNAB_TRANSACTIONS_SPEC.STORE_NAME_START, CNAB_TRANSACTIONS_SPEC.STORE_NAME_END).trim();

          const store = await this.prisma.store.upsert({
            where: { name: storeName },
            update: {},
            create: {
              name: storeName,
              owner: storeOwner,
            },
          });

          return this.prisma.transaction.create({
            data: {
              transactionTypeId,
              date: dateOffsetAdjusted,
              value,
              CPF,
              card,
              storeId: store.id,
            },
          });
        },
      ),
    );

    return parsedTransactions;
  }
}
