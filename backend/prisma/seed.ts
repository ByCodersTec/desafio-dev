import { Prisma, PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

async function main() {
  await prisma.transactionType.createMany({
    data: [
      {
        id: 1,
        description: 'DEBIT_PAYMENT',
        entryNature: 'DEBIT',
      },
      {
        id: 2,
        description: 'BOLETO_PAYMENT',
        entryNature: 'CREDIT',
      },
      {
        id: 3,
        description: 'FINANCING',
        entryNature: 'CREDIT',
      },
      {
        id: 4,
        description: 'CREDIT_PAYMENT',
        entryNature: 'DEBIT',
      },
      {
        id: 5,
        description: 'LOAN_PAYMENT',
        entryNature: 'DEBIT',
      },
      {
        id: 6,
        description: 'SALES',
        entryNature: 'DEBIT',
      },
      {
        id: 7,
        description: 'TED_PAYMENT',
        entryNature: 'DEBIT',
      },
      {
        id: 8,
        description: 'DOC_PAYMENT',
        entryNature: 'DEBIT',
      },
      {
        id: 9,
        description: 'RENT',
        entryNature: 'CREDIT',
      },
    ],
    skipDuplicates: true,
  });
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
