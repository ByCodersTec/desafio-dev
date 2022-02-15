import { Router } from 'express';
const multer = require('multer');

import StoresRoutes from './stores';
import TransactionsRoutes from './transactions';
import TransactionsTypesRoutes from './transactionsTypes';

import uploadHandler from '../domains/UploadToParse';
import authenticateHandler from '../domains/Authenticate';

const router = Router();

router.get('/', (request, response) => response.status(200).json({
  message: 'Status Ok',
}));

router.post('/authenticate', authenticateHandler);

const storage = multer.memoryStorage();
const uploader = multer({ storage });
router.post('/upload-to-parser', uploader.single('transactions'), uploadHandler);

router.use('/stores', StoresRoutes);
router.use('/transactions', TransactionsRoutes);
router.use('/transactions-types', TransactionsTypesRoutes);

export default router;
