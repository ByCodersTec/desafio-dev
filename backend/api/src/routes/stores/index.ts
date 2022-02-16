import { Router } from 'express';
import { createController, createSchema } from '../../domains/Stores/CreateStore';
import { getAllController } from '../../domains/Stores/GetAllStores';
import { getController } from '../../domains/Stores/GetStore';
import { updateController, updateSchema } from '../../domains/Stores/UpdateStore';
import { deleteController } from '../../domains/Stores/DeleteStore';

const router = Router();

router.post('/', createSchema, (request, response) => createController.handle(request, response));
router.get('/', (request, response) => getAllController.handle(request, response));
router.get('/:id', (request, response) => getController.handle(request, response));
router.put('/:id', updateSchema, (request, response) => updateController.handle(request, response));
router.delete('/:id', (request, response) => deleteController.handle(request, response));

export default router;
