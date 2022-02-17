module.exports = {
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:64649',
                ws: true,
                changeOrigin: true
            }
        }
    }
}