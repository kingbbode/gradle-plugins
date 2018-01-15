const webpack = require('webpack');
const path = require('path');
const webpackMerge = require('webpack-merge');
const CommonsChunkPlugin = require("webpack/lib/optimize/CommonsChunkPlugin");
const ExtractTextPlugin = require('extract-text-webpack-plugin');

const config = {
    entry: {
        app: path.resolve(__dirname, 'src/app.js')
    },
    output: {
        path: path.resolve(__dirname, 'src/output/static'),
        filename: 'js/[name].js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            ['es2015', {moduels: false}]
                        ]
                    }
                }]
            },
            {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: "css-loader",
                })
            },
            {
                test: /\.(jpe?g|png|gif|svg)$/,
                loader: 'url-loader',
                options: {
                    publicPath : '/static/',
                    name : 'images/[name].[ext]',
                    limit : 10000
                }
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)$/,
                loader: 'url-loader',
                options: {
                    publicPath : '/static/',
                    name : 'fonts/[name].[ext]',
                    limit : 10000
                }
            }
        ]
    },
    plugins : [
        new ExtractTextPlugin('css/[name].css')
    ]
};
module.exports = function(env) {
    return webpackMerge(config, require(`./webpack.${env}.js`))
};