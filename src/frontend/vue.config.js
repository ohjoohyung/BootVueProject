module.exports = {
  "outputDir": "C:\\Users\\xntm3\\IdeaProjects\\BootVueProject\\src\\main\\resources\\static",
  "devServer": {
    "proxy": {
      "/api": {
        "target": "http://localhost:9000",
        "ws": true,
        "changeOrigin": true
      }
    }
  },
  "transpileDependencies": [
    "vuetify"
  ]
}