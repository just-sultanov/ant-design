const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
const UglifyJsPlugin = require("uglifyjs-webpack-plugin");

module.exports = {
  mode: 'production',
  performance: {
    hints: false
  },
  optimization: {
    minimizer: [
      new UglifyJsPlugin({
        cache: true,
        parallel: true,
        sourceMap: true
      }),
      new OptimizeCSSAssetsPlugin({})
    ]
  },
  entry: './src/main/js/index.js',
  output: {
    filename: 'index.min.js',
    path: __dirname + '/resources/public/assets'
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: 'app.min.css'
    })
  ],
  module: {
    rules: [
      {
        test: /\.less$/,
        use: [{
          loader: 'style-loader',
        },
          {
            loader: MiniCssExtractPlugin.loader
          },
          {
            loader: 'css-loader'
          }, {
            loader: 'less-loader',
            options: {
              javascriptEnabled: true
            }
          }]
      },
    ]
  }
};
