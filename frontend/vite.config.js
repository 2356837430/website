/**
 * Vite配置文件
 * 配置Vue3项目的构建和开发服务器设置
 */

import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 自动导入插件
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    // Vue3插件
    vue(),
    
    // 自动导入Vue API和Element Plus组件
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    
    // 自动导入组件
    Components({
      resolvers: [ElementPlusResolver()],
    })
  ],
  
  // 路径别名配置
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  
  // 开发服务器配置
  server: {
    port: 8080, // 开发服务器端口
    host: true, // 允许外部访问
  }
})
