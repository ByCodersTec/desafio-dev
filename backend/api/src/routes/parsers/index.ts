import { Router } from 'express';
import { getAllController } from '../../domains/ParserStatus/GetAllParsers';
import { getController } from '../../domains/ParserStatus/GetParser';
import { deleteController } from '../../domains/ParserStatus/DeleteParser';
import { updateController, updateSchema } from '../../domains/ParserStatus/UpdateParser';

const router = Router();

router.get('/', (request, response) => getAllController.handle(request, response));
router.get('/:id', (request, response) => getController.handle(request, response));
router.delete('/:id', (request, response) => deleteController.handle(request, response));
router.put('/:id', updateSchema, (request, response) => updateController.handle(request, response));

export default router;
