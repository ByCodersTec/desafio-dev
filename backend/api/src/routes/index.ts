import { Router } from 'express';
import StoresRoutes from './stores';
import TransactionsRoutes from './transactions';
import TransactionsTypesRoutes from './transactionsTypes';

const router = Router();

router.get('/', (request, response) => response.status(200).json({
  message: 'Status Ok',
}));

router.use('/stores', StoresRoutes);
router.use('/transactions', TransactionsRoutes);
router.use('/transactions-types', TransactionsTypesRoutes);

export default router;
