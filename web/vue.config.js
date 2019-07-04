// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin

module.exports = {

    pages: {
        dashboard: {
            entry: 'src/main.js',
            template: 'public/monitorDashboard.html',
            filename: 'monitorDashboard.html'
        }
    },

    // publicPath: process.env.NODE_ENV === 'production' ? '/context/' : '/'

    /*
        configureWebpack: {
            plugins: [new BundleAnalyzerPlugin()]
        },
     */

    // Output directory for built UI
    // Spring Framework reads UI from './resources/static'
    outputDir: 'dist',

    // Output path for generated index.html (relative to `outputDir`).
    indexPath: 'index.html'
}

// Reference https://cli.vuejs.org/config