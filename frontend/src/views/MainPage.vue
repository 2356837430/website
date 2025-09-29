<template>
  <div class="main-page">
    <!-- 顶部图片背景区域 -->
    <div class="image-header fade-in-up">
    </div>

    <!-- 主要内容区域 -->
    <div class="main-content">
      <!-- 退出登录按钮 -->
      <div class="logout-section fade-in-up">
        <el-button @click="logout" type="primary" class="logout-btn">
          退出登录
        </el-button>
      </div>

      <!-- 主标题 -->
      <div class="title-section fade-in-up">
        <h1 class="main-title">进入安全调查的学生签证</h1>
      </div>

      <!-- 描述文本区域 -->
      <div class="description-section fade-in-up">
        <p class="description-text">
          当开学日一天天临近，而你的 tracker 账号却一直停留在 “background verification” 阶段，
          你很可能会想尽各种办法去加快学签进度：
          在 IRCC webform 上催促、向 IRCC 或 CBSA 申请调档、联系加拿大国会议员求助，
          甚至尝试致电加拿大驻华使领馆……
          一轮又一轮的尝试之后，很多人都会陷入自我怀疑：
          我的学生签证是不是进了“安全调查”？
        </p>
        <p class="description-text">
          <strong>事先申明：</strong>本网站所汇集的资料主要来自加拿大官方公开渠道以及微信公众号枫叶红了BUT红叶疯了等信息平台与个案分享。
          我无法为每一条信息的绝对准确性做出保证，但力求避免误导并尽可能为你提供可靠的参考。
          如果你有充足的时间，我强烈建议你阅读枫叶红了BUT红叶疯了微信公众号的文章，里面有非常详细的关于安全调查的介绍。
        </p>
        <h3 class="section-title">关于“安全调查”的定义</h3>
        <p class="description-text">
          加拿大对入境与移民申请实行多层次的安全审查，
          主要由移民、难民及公民事务部（IRCC）与边境服务局（CBSA）、加拿大安全情报局（CSIS）等部门协同开展。
          该审查会结合生物识别信息（如指纹与照片）与个人背景资料，
          将申请人的信息与国内外的移民、刑事及情报数据库比对，
          以识别可能对国家安全或公共安全构成的风险（例如与国家安全、国际人权侵犯或有组织犯罪相关的事项）。
          在审查过程中，可能会要求补充材料、进行进一步信息核实，甚至安排安全面谈，
          因此安全审查常常会导致签证处理出现显著延迟。
          最终的签证决定由 IRCC 作出，
          CSIS 与 CBSA 则向 IRCC 提供安全建议与情报支持。
          <br>
          <a href="https://www.canada.ca/en/immigration-refugees-citizenship/corporate/transparency/committees/secu-august-28-2024/immigration-security-screening-trilateral-program.html?utm_source=chatgpt.com" target="_blank" rel="noopener noreferrer">官方安全审查介绍（英文原文）</a>
          <br>
          <a href="https://search.open.canada.ca/qpnotes/record/cic%2CIRCC-2025-QP-00014?utm_source=chatgpt.com" target="_blank" rel="noopener noreferrer">官方答疑公告（英文原文）</a>
          <div class="agency-cards">
            <div class="agency-card ircc-card">
              <h4 class="agency-title">IRCC（移民、难民和公民事务部）</h4>
              <p class="agency-desc">Visa Officer审阅申请人递交的签证材料并确保材料完整，做出eligibility和admissibility决定。申请人的eligibility通过后，如Visa Officer无法确定申请人的admissibility，则需要将申请转送至CBSA和CSIS进行进一步调查并根据他们的调查结果判断是否批准签证或拒签。</p>
            </div>

            <div class="agency-card cbsa-card">
              <h4 class="agency-title">CBSA（加拿大边境服务局）</h4>
              <p class="agency-desc">CBSA的国家安全检查司(NSSD)对申请人的材料背景进行审核，判断是否有触发A34（Security）、A35（International Rights Violations）、A37（Organized criminality）的不可受理项目。NSSD会通过开源途径收集分类信息并研究所有可以得到的信息并进行分析，给出该申请是否满足admissibility的结论，将其返回至IRCC。</p>
            </div>

            <div class="agency-card csis-card">
              <h4 class="agency-title">CSIS（加拿大安全情报局）</h4>
              <p class="agency-desc">CSIS会给出申请人是否可能威胁加拿大国家安全的建议，特别是对于A34(1)(a)、(c)、(d)、(f)项。CSIS更注重于申请人是否可能在现在或未来对加拿大国家安全造成威胁，但是CSIS不会对申请人做出是否满足admissibility的建议。</p>
            </div>
          </div>
        </p>
        <p class="description-text">
          上面这段话是官方定义，可以看出安全调查是由加拿大多个部门协同开展的，这意味着进入安调的学生签证处理时间往往会远远超过正常的预期时间。
          （在加拿大境外学生签证的处理时间在中国只需约 3–4 周，若在加拿大境内递交申请，处理时间大致在大约 10–12 周左右。）
        </p>
      </div>

             <!-- 导航卡片区域 -->
      <div class="navigation-section fade-in-up">
        <div 
          @click="router.push('/page1')" 
          class="nav-card"
        >
          <h3 class="card-title">为什么学签会进入安调</h3>
          <p class="card-desc">了解哪些情况会触发安全调查</p>
        </div>
        <div 
          @click="router.push('/page2')" 
          class="nav-card"
        >
          <h3 class="card-title">安全调查的具体流程</h3>
          <p class="card-desc">了解调查时间线和预期周期</p>
        </div>
        <div 
          @click="router.push('/page3')" 
          class="nav-card"
        >
          <h3 class="card-title">强制令可以结束安调吗</h3>
          <p class="card-desc">了解安调与强制令的关系</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
/**
 * 主页面组件
 * 展示安全调查相关信息，提供导航到各个详情页面的功能
 */

import router from "@/router"
import { ElMessage } from "element-plus"
import { usePageAnimation } from "@/composables/usePageAnimation"
import { post, removeToken } from "@/net"

/**
 * 处理用户退出登录
 * 清空用户会话信息并跳转到登录页面
 */
const logout = () => {
  // 调用后端登出接口（可选）
  post('/api/users/logout', {}, () => {
    // 清空本地存储
    removeToken()
    ElMessage.success('已退出登录')
    router.push("/")
  }, () => {
    // 即使后端调用失败，也清空本地存储
    removeToken()
    ElMessage.success('已退出登录')
    router.push("/")
  })
}

// 启用页面动画效果
usePageAnimation()
</script>

<style scoped>
/* 主容器：最小高度占满视口，使用更淡的背景色突出卡片 */
.main-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #F8F4F0 0%, #F0E8E0 50%, #E8E0D8 100%); /* 更淡的背景色 */
}



/* 顶部图片背景区域：使用main_page.jpg作为背景 */
.image-header {
  background: url('@/img/main_page.jpg') center/cover no-repeat; /* 背景图片居中覆盖 */
  min-height: 200px; /* 最小高度 */
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 主要内容区域：页面主体内容容器 */
.main-content {
  padding: 20px; /* 减小内边距 */
  position: relative;
  max-width: 1200px; /* 最大宽度限制 */
  margin: 0 auto; /* 水平居中 */
  width: 100%;
}

/* 退出登录按钮区域：固定在右上角，与三个页面保持一致 */
.logout-section {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
}

/* 退出登录按钮样式 */
.logout-btn {
  background: #8B4513; /* 深棕色背景 */
  border: none;
  padding: 20px 40px; /* 与导航按钮保持一致 */
  font-size: 20px; /* 与导航按钮保持一致 */
  font-weight: 500;
  border-radius: 10px; /* 与导航按钮保持一致 */
  min-width: 160px; /* 与导航按钮保持一致 */
  box-shadow: 0 3px 10px rgba(139, 69, 19, 0.25); /* 与导航按钮保持一致 */
}

/* 退出登录按钮悬停效果 */
.logout-btn:hover {
  background: #A0522D; /* 悬停时颜色变深 */
  transform: translateY(-2px); /* 向上移动2px */
  box-shadow: 0 4px 12px rgba(139, 69, 19, 0.3); /* 悬停时添加阴影 */
}

/* 标题区域：页面主标题容器 */
.title-section {
  text-align: center;
  margin-bottom: 80px;
  margin-top: 40px;
}

/* 主标题样式 */
.main-title {
  font-size: 42px;
  font-weight: bold;
  color: #8B4513; /* 深棕色 */
  margin: 0;
  line-height: 1.2;
}

/* 描述文本区域：长文本内容容器 */
.description-section {
  margin-bottom: 50px;
  line-height: 1.8; /* 行高 */
}

/* 描述文本段落样式 */
.description-text {
  font-size: 20px;
  color: #333; /* 深灰色文字 */
  margin-bottom: 20px;
  text-align: justify; /* 两端对齐 */
}

/* 章节标题样式 */
.section-title {
  font-size: 24px;
  font-weight: bold;
  color: #8B4513; /* 深棕色 */
  margin: 30px 0 15px 0;
  border-left: 4px solid #8B4513; /* 左侧边框装饰 */
  padding-left: 15px;
}

/* 超链接样式 */
a {
  color: #8B4513; /* 深棕色 */
  text-decoration: none; /* 去除下划线 */
  font-weight: 500; /* 稍微加粗 */
  border-bottom: 1px solid transparent; /* 透明下边框 */
  transition: all 0.3s ease; /* 平滑过渡效果 */
}

/* 超链接悬停效果 */
a:hover {
  color: #A0522D; /* 悬停时颜色稍微变浅 */
  border-bottom: 1px solid #8B4513; /* 悬停时显示下边框 */
  text-decoration: none; /* 确保悬停时也没有下划线 */
}

/* 超链接访问后的样式 */
a:visited {
  color: #8B4513; /* 访问后保持深棕色 */
}

/* 机构卡片样式 */
.agency-cards {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin: 30px 0;
}

.agency-card {
  padding: 25px;
  border-radius: 12px;
  border-left: 5px solid;
  box-shadow: 0 4px 12px rgba(139, 69, 19, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.agency-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(139, 69, 19, 0.15);
}

.ircc-card {
  background: linear-gradient(135deg, #E8F4FD 0%, #F0F8FF 100%);
  border-left-color: #4A90E2;
}

.cbsa-card {
  background: linear-gradient(135deg, #E8F5E8 0%, #F0FFF0 100%);
  border-left-color: #5CB85C;
}

.csis-card {
  background: linear-gradient(135deg, #FFF8E1 0%, #FFFBF0 100%);
  border-left-color: #F0AD4E;
}

.agency-title {
  font-size: 20px;
  font-weight: bold;
  color: #8B4513;
  margin: 0 0 15px 0;
}

.agency-desc {
  font-size: 18px;
  color: #333;
  line-height: 1.6;
  margin: 0;
}

/* 导航卡片区域：三个功能卡片的容器，均匀分布在整个屏幕宽度 */
.navigation-section {
  display: flex;
  justify-content: space-between; /* 均匀分布 */
  align-items: stretch; /* 拉伸对齐，让卡片高度一致 */
  margin-top: 20px; /* 减小顶部间距 */
  margin-bottom: 30px; /* 减小底部间距 */
  padding: 0 20px; /* 减小左右边距 */
  width: 100%; /* 使用100%而不是100vw，避免水平滚动 */
  box-sizing: border-box; /* 包含内边距和边框 */
  gap: 50px; /* 增加卡片之间的间距 */
}

/* 导航卡片样式 */
.nav-card {
  background: rgba(236, 222, 214, 0.95); /* 更深的背景色，突出卡片 */
  border: 2px solid rgba(139, 69, 19, 0.15); /* 稍微加深的边框 */
  padding: 40px 30px; /* 增大内边距 */
  border-radius: 15px; /* 圆角 */
  flex: 1; /* 让卡片平均分配空间 */
  min-height: 100px; /* 设置最小高度 */
  box-shadow: 0 6px 20px rgba(139, 69, 19, 0.15); /* 增强阴影效果 */
  cursor: pointer; /* 鼠标指针 */
  transition: all 0.3s ease; /* 平滑过渡 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  position: relative; /* 相对定位 */
}

/* 卡片标题样式 */
.card-title {
  font-size: 26px; /* 增大字体 */
  font-weight: bold;
  color: #8B4513; /* 深棕色 */
  margin: 0 0 15px 0;
  line-height: 1.3;
}

/* 卡片描述样式 */
.card-desc {
  font-size: 20px; /* 增大字体 */
  color: #666; /* 灰色文字 */
  margin: 0;
  line-height: 1.5;
}

/* 导航卡片悬停效果 */
.nav-card:hover {
  background: rgba(243, 227, 216, 1); /* 悬停时背景完全不透明 */
  transform: translateY(-8px); /* 向上移动8px */
  box-shadow: 0 12px 30px rgba(139, 69, 19, 0.2); /* 增强悬停阴影 */
  border-color: rgba(139, 69, 19, 0.3); /* 悬停时边框加深 */
}

/* 响应式设计：移动端适配 */
@media (max-width: 768px) {
  .main-content {
    padding: 15px; /* 移动端进一步减少内边距 */
  }
  
  .main-title {
    font-size: 28px; /* 移动端字体缩小 */
    line-height: 1.3;
    padding: 0 10px; /* 添加左右内边距 */
  }
  
  .description-text {
    font-size: 18px; /* 移动端字体大小 */
    line-height: 1.6;
    margin-bottom: 18px;
  }
  
  .section-title {
    font-size: 22px; /* 移动端字体大小 */
    margin: 25px 0 12px 0;
    padding-left: 12px;
  }
  
  /* 机构卡片移动端优化 */
  .agency-cards {
    gap: 15px;
    margin: 25px 0;
  }
  
  .agency-card {
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 15px;
  }
  
  .agency-title {
    font-size: 18px;
    margin-bottom: 12px;
  }
  
  .agency-desc {
    font-size: 16px;
    line-height: 1.5;
  }
  
  .navigation-section {
    flex-direction: column; /* 移动端卡片垂直排列 */
    align-items: center;
    gap: 25px; /* 移动端卡片间距 */
    padding: 0 10px; /* 移动端边距 */
    width: 100%; /* 移动端恢复正常宽度 */
    margin-top: 30px;
  }
  
  .nav-card {
    width: 100%; /* 移动端卡片占满宽度 */
    max-width: 380px; /* 限制最大宽度 */
    padding: 35px 25px; /* 移动端调整内边距 */
    min-height: 160px; /* 移动端调整高度 */
    flex: none; /* 移动端取消flex:1 */
    border-radius: 12px;
    /* 增强触摸反馈 */
    transition: all 0.2s ease;
    touch-action: manipulation;
  }
  
  .nav-card:active {
    transform: translateY(-4px) scale(0.98);
    box-shadow: 0 8px 20px rgba(139, 69, 19, 0.2);
  }
  
  .card-title {
    font-size: 22px; /* 移动端标题字体 */
    line-height: 1.2;
    margin-bottom: 12px;
  }
  
  .card-desc {
    font-size: 17px; /* 移动端描述字体 */
    line-height: 1.4;
  }
  
  .logout-section {
    top: 15px;
    right: 15px;
  }
  
  .logout-btn {
    padding: 16px 32px; /* 移动端调整退出按钮 */
    font-size: 17px;
    min-width: 140px;
    border-radius: 8px;
    /* 增强触摸反馈 */
    touch-action: manipulation;
    transition: all 0.2s ease;
  }
  
  .logout-btn:active {
    transform: translateY(-1px) scale(0.98);
  }
  
  .image-header {
    min-height: 150px; /* 移动端减少图片高度 */
  }
  
  .title-section {
    margin-bottom: 60px; /* 移动端调整间距 */
    margin-top: 30px;
  }
  
  .description-section {
    margin-bottom: 40px;
  }
}

/* 小屏幕手机适配 */
@media (max-width: 480px) {
  .main-content {
    padding: 12px;
  }
  
  .main-title {
    font-size: 24px;
    padding: 0 8px;
  }
  
  .description-text {
    font-size: 16px;
    line-height: 1.5;
  }
  
  .section-title {
    font-size: 20px;
    padding-left: 10px;
  }
  
  .agency-card {
    padding: 18px;
  }
  
  .agency-title {
    font-size: 17px;
  }
  
  .agency-desc {
    font-size: 15px;
  }
  
  .nav-card {
    max-width: 100%;
    padding: 30px 20px;
    min-height: 140px;
  }
  
  .card-title {
    font-size: 20px;
  }
  
  .card-desc {
    font-size: 16px;
  }
  
  .logout-btn {
    padding: 14px 28px;
    font-size: 16px;
    min-width: 120px;
  }
  
  .image-header {
    min-height: 120px;
  }
}
</style>
