import { Router } from 'express';
import { createController, createSchema } from '../../domains/TransactionsTypes/CreateTransactionType';
import { getAllController } from '../../domains/TransactionsTypes/GetAllTransactionsTypes';
import { getController } from '../../domains/TransactionsTypes/GetTransactionType';
import { updateController, updateSchema } from '../../domains/TransactionsTypes/UpdateTransactionType';
import { deleteController } from '../../domains/TransactionsTypes/DeleteTransactionType';

const router = Router();

router.post('/', createSchema, (request, response) => createController.handle(request, response));
router.get('/', (request, response) => getAllController.handle(request, response));
router.get('/:id', (request, response) => getController.handle(request, response));
router.put('/:id', updateSchema, (request, response) => updateController.handle(request, response));
router.delete('/:id', (request, response) => deleteController.handle(request, response));

export default router;
