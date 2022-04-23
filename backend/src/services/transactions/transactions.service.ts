import { Injectable } from '@nestjs/common';
import { Prisma, Transaction } from '@prisma/client';
import { addHours, parse } from 'date-fns';
import { PrismaService } from 'src/database/prisma/prisma.service';
import {
  CNAB_TRANSACTIONS_SPEC,
  RAW_TRANSACTIONS_TIME_OFFSET,
} from './transactions.service.constants';

type ParsedTransaction = Omit<Transaction, 'id'>;

@Injectable()
export class TransactionsService {
  constructor(private prisma: PrismaService) {}

  async transactions(params?: {
    skip?: number;
    take?: number;
    cursor?: Prisma.TransactionWhereUniqueInput;
    where?: Prisma.TransactionWhereInput;
    orderBy?: Prisma.TransactionOrderByWithRelationInput;
  }): Promise<Transaction[]> {
    return this.prisma.transaction.findMany(params);
  }

  async transactionsTypes(params?: {
    skip?: number;
    take?: number;
    cursor?: Prisma.TransactionTypeWhereUniqueInput;
    where?: Prisma.TransactionTypeWhereInput;
  }) {
    return this.prisma.transactionType.findMany({ ...params });
  }

  async processCNABFile(file: Express.Multer.File): Promise<Transaction[]> {
    const rawTransactions = file.buffer.toString().split(/(?:\r\n|\r|\n)/g);

    const parsedTransactions = await Promise.all(
      rawTransactions
        .filter((line) => !!line)
        .map<Promise<ParsedTransaction>>(async (rawTransaction) => {
          const transactionTypeId = Number(
            rawTransaction[CNAB_TRANSACTIONS_SPEC.TYPE],
          );

          const rawDate = rawTransaction.slice(
            CNAB_TRANSACTIONS_SPEC.DATE_START,
            CNAB_TRANSACTIONS_SPEC.DATE_END,
          );
          const rawTime = rawTransaction.slice(
            CNAB_TRANSACTIONS_SPEC.TIME_START,
            CNAB_TRANSACTIONS_SPEC.TIME_END,
          );

          const rawDateTime = `${rawDate}${rawTime}`;
          const parsedDate = parse(rawDateTime, 'yyyyMMddHHmmss', new Date());
          const dateOffsetAdjusted = addHours(
            parsedDate,
            RAW_TRANSACTIONS_TIME_OFFSET,
          );

          const value = Number(
            rawTransaction.slice(
              CNAB_TRANSACTIONS_SPEC.VALUE_START,
              CNAB_TRANSACTIONS_SPEC.VALUE_END,
            ),
          );
          const CPF = rawTransaction.slice(
            CNAB_TRANSACTIONS_SPEC.CPF_START,
            CNAB_TRANSACTIONS_SPEC.CPF_END,
          );
          const card = rawTransaction.slice(
            CNAB_TRANSACTIONS_SPEC.CARD_START,
            CNAB_TRANSACTIONS_SPEC.CARD_END,
          );
          const storeOwner = rawTransaction
            .slice(
              CNAB_TRANSACTIONS_SPEC.STORE_OWNER_START,
              CNAB_TRANSACTIONS_SPEC.STORE_OWNER_END,
            )
            .trim();
          const storeName = rawTransaction
            .slice(
              CNAB_TRANSACTIONS_SPEC.STORE_NAME_START,
              CNAB_TRANSACTIONS_SPEC.STORE_NAME_END,
            )
            .trim();

          const store = await this.prisma.store.upsert({
            where: { name: storeName } as Prisma.StoreWhereUniqueInput,
            update: {},
            create: {
              name: storeName,
              owner: storeOwner,
            } as Prisma.StoreCreateInput,
          });

          return {
            transactionTypeId,
            date: dateOffsetAdjusted,
            value,
            CPF,
            card,
            storeId: store.id,
          };
        }),
    );

    return this.prisma.$transaction(
      parsedTransactions.map((transaction: ParsedTransaction) =>
        this.prisma.transaction.create({ data: transaction }),
      ),
    );
  }
}
