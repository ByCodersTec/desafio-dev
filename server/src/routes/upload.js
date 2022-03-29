const express = require('express');
const router = express.Router();
const multipart = require('connect-multiparty');
const readcnab = require('../services/readcnab');

/**
 * @swagger
 *   /api/v1/upload:
 *   post:
 *     summary: Sending the txt file
 *     description: Interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco de dados relacional, se atente as documentações que estão logo abaixo.
 */

const multipartMiddleware = multipart({ uploadDir: 'src/uploads' });
router.post('/', multipartMiddleware, async function (req, res) {
  try {
    const files = req.files;
    res.json(await readcnab.cnabRead(files));
  } catch (err) {
    console.error(`Error`, err.message);
    res.status(err.statusCode || 500).json({ message: err.message });
  }
});

/**
 * @swagger
 *   /api/v1/list:
 *   post:
 *     summary: Search for information to fill the CNAB list
 *     description: Interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco de dados relacional
 */

router.get('/', async function (req, res, next) {
  try {
    res.json(await readcnab.getList());
  } catch (err) {
    console.error(`Error `, err.message);
    res.status(err.statusCode || 500).json({ message: err.message });
  }
});

module.exports = router;
