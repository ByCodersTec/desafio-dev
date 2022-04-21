import { Injectable } from '@nestjs/common';
import { Prisma } from '@prisma/client';
import { PrismaService } from 'src/database/prisma/prisma.service';

@Injectable()
export class TransactionsService {
    constructor(private prisma: PrismaService) { }

    async transactionsTypes(params?: {
        skip?: number;
        take?: number;
        cursor?: Prisma.TransactionTypeWhereUniqueInput;
        where?: Prisma.TransactionTypeWhereInput;
    }) {
        return this.prisma.transactionType.findMany({...params})
    }
}
