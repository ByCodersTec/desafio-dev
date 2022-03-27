const express = require('express');
const router = express.Router();
const multipart = require('connect-multiparty');

const multipartMiddleware = multipart({ uploadDir: './src/uploads' });
router.post('/upload', multipartMiddleware, (req, res) => {
  const files = req.files;
  console.log(files);
  res.json({ message: files });
});

module.exports = router;
