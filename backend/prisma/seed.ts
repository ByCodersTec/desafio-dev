import {
    EntryNature,
    PrismaClient,
    TransactionDescription
} from '.prisma/client';

const prisma = new PrismaClient();

async function main() {
  const transactionTypes = [
    {
      id: 1,
      description: TransactionDescription.DEBIT_PAYMENT,
      entryNature: EntryNature.DEBIT,
    },
    {
      id: 2,
      description: TransactionDescription.BOLETO_PAYMENT,
      entryNature: EntryNature.CREDIT,
    },
    {
      id: 3,
      description: TransactionDescription.FINANCING,
      entryNature: EntryNature.CREDIT,
    },
    {
      id: 4,
      description: TransactionDescription.CREDIT_PAYMENT,
      entryNature: EntryNature.DEBIT,
    },
    {
      id: 5,
      description: TransactionDescription.LOAN_PAYMENT,
      entryNature: EntryNature.DEBIT,
    },
    {
      id: 6,
      description: TransactionDescription.SALES,
      entryNature: EntryNature.DEBIT,
    },
    {
      id: 7,
      description: TransactionDescription.TED_PAYMENT,
      entryNature: EntryNature.DEBIT,
    },
    {
      id: 8,
      description: TransactionDescription.DOC_PAYMENT,
      entryNature: EntryNature.DEBIT,
    },
    {
      id: 9,
      description: TransactionDescription.RENT,
      entryNature: EntryNature.CREDIT,
    },
  ];

  await Promise.all(
    transactionTypes.map(
      async ({
        id,
        description,
        entryNature,
      }) =>
        prisma.transactionType.upsert({
          where: { id },
          update: {},
          create: {
            id,
            description,
            entryNature,
          },
        }),
    ),
  );
}

main()
  .catch((e) => {
    console.error(e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
