const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  devtool: 'source-map',
  output: {
    filename: 'bundle.js'
  },
  module: {
    rules: [{
      test: /\.(js|jsx)$/,
      exclude: /node_modules/,
      loader: "babel-loader",
      options: {
        presets: ['@babel/preset-env', '@babel/preset-react']
      }
    },{
        test: /\.css$/,
        exclude: /node_modules/,
        use: ['style-loader', 'css-loader']
    }]
  },
  resolve: {
    extensions: ['.js', '.jsx']
  },
  devServer: {
      port: 3000
  },
  entry: './src/main/webapp/main.js',
  plugins: [
    new HtmlWebpackPlugin({
      template: "src/main/resources/static/index.html"
    })
  ]
};