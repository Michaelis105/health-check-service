// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin

module.exports = {
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