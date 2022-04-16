const path = require('path');

module.exports = {
  // outputDir: './front/dist',
  // assetsDir: './front/web',
  pages: {
    index: {
      entry: './front/web/main.ts',
      template: 'front/public/index.html',
      filename: 'index.html',
    },
  },
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@', path.join(__dirname, './front/web'));
  },
};
