import { Injectable } from '@nestjs/common';
import { Prisma, Transaction } from '@prisma/client';
import { addHours, parse } from 'date-fns';
import { PrismaService } from 'src/database/prisma/prisma.service';

const RAW_TRANSACTIONS_TIME_OFFSET = 3; // Transactions Time in Txt File are in UTC-3, check README

@Injectable()
export class TransactionsService {
  constructor(private prisma: PrismaService) {}

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

          const rawDate = `${rawTransaction.slice(1, 9)}${rawTransaction.slice(
            42,
            48,
          )}`;
          const parsedDate = parse(rawDate, 'yyyyMMddHHmmss', new Date());
          const dateOffsetAdjusted = addHours(
            parsedDate,
            RAW_TRANSACTIONS_TIME_OFFSET,
          );

          const value = Number(rawTransaction.slice(9, 19));
          const CPF = rawTransaction.slice(19, 30);
          const card = rawTransaction.slice(30, 42);
          const storeOwner = rawTransaction.slice(48, 62).trim();
          const storeName = rawTransaction.slice(62, 81).trim();

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
              transactionTypeId: Number(rawTransaction[0]),
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
