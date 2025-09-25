/**
 * 页面动画组合式函数
 * 提供页面元素的渐入动画效果，支持路由切换时的动画重新触发
 */

import { onMounted, nextTick } from 'vue'
import { useRoute } from 'vue-router'
import { watch } from 'vue'

/**
 * 页面动画组合式函数
 * @returns {Object} 包含动画触发函数的对象
 */
export function usePageAnimation() {
  const route = useRoute()

  /**
   * 触发页面动画效果
   * 为所有带有fade-in-up类的元素添加动画效果
   */
  const triggerAnimation = () => {
    // 移除所有元素的动画类，重置动画状态
    const elements = document.querySelectorAll('.fade-in-up')
    elements.forEach(element => {
      element.classList.remove('animate')
    })
    
    // 等待DOM更新后重新触发动画
    nextTick(() => {
      setTimeout(() => {
        elements.forEach((element, index) => {
          setTimeout(() => {
            element.classList.add('animate')
          }, index * 200) // 每个元素延迟200ms，创建错落有致的动画效果
        })
      }, 100) // 初始延迟100ms
    })
  }

  // 页面加载完成后触发动画
  onMounted(() => {
    triggerAnimation()
  })

  // 监听路由变化，当进入任何页面时重新触发动画
  watch(() => route.path, (newPath) => {
    // 延迟一点确保页面切换完成
    setTimeout(() => {
      triggerAnimation()
    }, 50)
  })

  return {
    triggerAnimation
  }
}
