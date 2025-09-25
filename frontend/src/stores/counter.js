/**
 * 计数器状态管理
 * 使用Pinia进行状态管理，提供计数器的基本功能
 */

import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

/**
 * 计数器状态管理store
 * 提供计数器的状态和操作方法
 */
export const useCounterStore = defineStore('counter', () => {
  // 计数器状态
  const count = ref(0)
  
  // 计算属性：双倍计数
  const doubleCount = computed(() => count.value * 2)
  
  /**
   * 增加计数器
   */
  function increment() {
    count.value++
  }

  return { count, doubleCount, increment }
})
