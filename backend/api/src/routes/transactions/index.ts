import { Router } from 'express';
import { createController, createSchema } from '../../domains/Transactions/CreateTransaction';
import { getAllController } from '../../domains/Transactions/GetAllTransactions';
import { getController } from '../../domains/Transactions/GetTransaction';
import { updateController, updateSchema } from '../../domains/Transactions/UpdateTransaction';
import { deleteController } from '../../domains/Transactions/DeleteTransaction';

const router = Router();

router.post('/', createSchema, (request, response) => createController.handle(request, response));
router.get('/', (request, response) => getAllController.handle(request, response));
router.get('/:id', (request, response) => getController.handle(request, response));
router.put('/:id', updateSchema, (request, response) => updateController.handle(request, response));
router.delete('/:id', (request, response) => deleteController.handle(request, response));

export default router;
