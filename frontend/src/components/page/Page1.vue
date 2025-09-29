<template>
  <div class="definition-page">
    <!-- 返回按钮 - 固定在右上角 -->
    <div class="back-button-section fade-in-up">
      <el-button @click="router.push('/main')" type="primary" class="back-btn">
        返回主页
      </el-button>
    </div>

    <!-- 主要内容区域 -->
    <div class="main-content">
      <div class="title-section fade-in-up">
        <h1 class="page-title">为什么学签会进入安调</h1>
      </div>
      
        <div class="content-section fade-in-up">
          <h2 class="section-title">加拿大学签安全调查定义</h2>
          <p class="description-text">
            加拿大学签安全调查是由加拿大移民、难民和公民事务部（IRCC）、加拿大边境服务局（CBSA）和加拿大安全情报局（CSIS）共同负责的审查程序。该程序旨在确保申请人不会对加拿大的国家安全构成威胁，同时验证申请人的真实意图和背景信息。
          </p>
          <p class="description-text">
            根据加拿大《移民和难民保护法》（IRPA）第34、35和37节的规定，安全调查主要针对以下三个方面：
          </p>
          <ul class="content-list">
            <li><strong>VIT34：</strong>涉及国家安全或公共安全的人员</li>
            <li><strong>VIT35：</strong>涉及国际人权侵犯的人员</li>
            <li><strong>VIT37：</strong>涉及有组织犯罪的人员</li>
          </ul>

          <h3 class="section-title">触发安全调查的主要因素</h3>
          <p class="description-text">
            根据CBSA的《移民控制手册》（Immigration Control Manual）和官方数据，以下因素可能触发安全调查：
          </p>
          
          <h4 class="subsection-title">1. 职业背景因素</h4>
          <ul class="content-list">
            <li>曾在军队、警察、情报机构工作</li>
            <li>在核工业、生化、航空航天等敏感领域工作</li>
            <li>在政府部门或国有企业担任要职</li>
            <li>在高科技企业从事敏感技术研发</li>
          </ul>

          <h4 class="subsection-title">2. 教育背景因素</h4>
          <ul class="content-list">
            <li>就读于国防科技大学、北京邮电大学等敏感院校</li>
            <li>专业涉及计算机科学、电子通信工程、航天技术等</li>
            <li>在涉及武器开发或军事技术的领域接受过高等教育</li>
          </ul>

          <h4 class="subsection-title">3. 国籍和旅行历史</h4>
          <ul class="content-list">
            <li>来自被加拿大视为高风险的国家或地区</li>
            <li>在冲突地区或恐怖活动高发地区有长期居住史</li>
            <li>频繁前往高风险国家的旅行记录</li>
          </ul>

          <h4 class="subsection-title">4. 其他因素</h4>
          <ul class="content-list">
            <li>有犯罪记录，特别是涉及暴力或恐怖主义</li>
            <li>与被关注的组织或个人有密切联系</li>
            <li>申请材料中存在不一致或虚假信息</li>
            <li>资金来源不明确或可疑</li>
          </ul>

          <h3 class="section-title">IRCC申请分类机制：Officer Rules与Model Rules</h3>
          <p class="description-text">
            签证申请提交后，IRCC首先会通过Officer Rules和/或Model Rules对申请按照Eligibility进行分类：
          </p>
          <ul class="content-list">
            <li><strong>Model Rules（模型规则）：</strong>使用机器学习识别历史数据中的模式，将其作为未来申请处理结果的预测指标</li>
            <li><strong>Officer Rules（官员规则）：</strong>使用历史趋势数据，由IRCC官员制定的规则</li>
          </ul>

          <h4 class="subsection-title">申请处理流程</h4>
          <p class="description-text">
            所有签证提交后需要先经过Officer Rules初步判断决定是否进入AI流程(Model Rules)，在这个过程中：
          </p>
          <ul class="content-list">
            <li><strong>如果某申请落入"Officer Rules"的适用范围：</strong>则会直接转交人工审核（即Officer Rules Applied为yes）。对于直接人工处理的申请，我们的猜测为在Officer Rules判断过程中，申请材料中的某些信息已经触发用于安调的安全审查指标(indicator)，所以在CBSA note中Security可能为Not Started，但是否真的进行安调还需要Senior Visa Officer(PM-03)在审阅申请材料后给出相应判断。</li>
            <li><strong>如果某申请未落入"Officer Rules"的适用范围：</strong>则会进入AI审理（即Officer Rules Applied为no）。AI不会对除Eligibility外的任何要求做出判断，只会对申请根据Model Rules进行Eligibility分类。</li>
          </ul>

          <h3 class="section-title">Admissibility审核</h3>
          <p class="description-text">
            根据CBSA的EINSSP报告，IRCC关于是否将申请转送入安调的决定是以安全审查指标(indicators)为指导的，这些指标帮助Visa Officer识别那些出于安全考虑可能不被允许进入加拿大的申请人。在CBSA的Immigration Control (IC) manual中，指标主要分为以下几类：
          </p>
          <ul class="content-list">
            <li><strong>Mandatory Indicator（强制指标）：</strong>标记对加拿大的安全和安保具有高风险的申请人，如果申请人满足一个或多个指标则必须进行安调</li>
            <li><strong>Discretionary Indicator（自由裁量指标）：</strong>对于满足该指标的申请者，Visa Officer需要继续阅读申请材料并判断是否需要进行安调后才可得出结论</li>
            <li><strong>Security Integrity Indicator(SII)：</strong>由CSIS研发并维护的帮助Visa Officer递交CSIS进行安调</li>
          </ul>

          <h4 class="subsection-title">指标更新与自由裁量权</h4>
          <p class="description-text">
            此外，CBSA在EINSSP报告中提到对于indicator在2020年后有所更新，新发布的专题指标与之前指标最大的区别在于赋予了IRCC官员自由裁量权。与原来的指标不同，新的指标允许他们使用自己的判断，并考虑所有的标准，以确定是否需要进一步的筛选，或者他们是否有足够的信息来批准或拒绝申请，而不需要安全检查合作伙伴的投入。
          </p>
          <p class="description-text">
            也就是说，IRCC官员在申请TR和PR时使用的前一版本的指标包含了触发强制转介（即转交给安全审查伙伴CBSA和CSIS进行安调）的指标，而现在，是否对申请进行转介由IRCC官员酌情作出。新指标引入的目的在于减少非必要的安调转送，但是根据IRCC的数据，IRCC官员并没有开始行使新指标赋予他们的更大自由裁量权。
          </p>


          <h3 class="section-title">如何判断是否进入安全调查</h3>
          <p class="description-text">
            申请人可以通过以下迹象判断自己的申请是否进入了安全调查：
          </p>
          <ul class="content-list">
            <li><strong>处理时间延长：</strong>超过正常处理周期（中国申请通常3-4周）</li>
            <li><strong>状态停滞：</strong>申请状态长时间停留在"Background verification"阶段</li>
            <li><strong>补充材料要求：</strong>被要求提供额外的背景证明材料</li>
            <li><strong>调档信息：</strong>通过GCMS调档发现Security状态为"In Progress"</li>
          </ul>
        </div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
import { usePageAnimation } from "@/composables/usePageAnimation";

// 使用全局页面动画
usePageAnimation();
</script>

<style scoped>
/* 主容器：使用与MainPage相同的背景渐变 */
.definition-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #F8F4F0 0%, #F0E8E0 50%, #E8E0D8 100%);
  position: relative;
}

/* 返回按钮区域：固定在右上角 */
.back-button-section {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
}

/* 返回按钮样式：与MainPage的退出按钮保持一致 */
.back-btn {
  background: #8B4513;
  border: none;
  padding: 20px 40px;
  font-size: 20px;
  font-weight: 500;
  border-radius: 10px;
  min-width: 160px;
  box-shadow: 0 3px 10px rgba(139, 69, 19, 0.25);
}

/* 返回按钮悬停效果 */
.back-btn:hover {
  background: #A0522D;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(139, 69, 19, 0.3);
}

/* 主要内容区域：与MainPage保持一致 */
.main-content {
  padding: 20px; /* 与MainPage保持一致 */
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
  padding-top: 80px; /* 为固定按钮留出空间 */
}

/* 标题区域 */
.title-section {
  text-align: center;
  margin-bottom: 80px; /* 与MainPage保持一致 */
  margin-top: 0; /* 调整上边距，与MainPage视觉效果保持一致 */
}

/* 页面标题样式：与MainPage保持一致 */
.page-title {
  font-size: 42px;
  font-weight: bold;
  color: #8B4513;
  margin: 0;
  line-height: 1.2;
}

/* 内容区域：直接显示在背景上 */
.content-section {
  margin-bottom: 40px;
}

/* 章节标题样式：与MainPage保持一致 */
.section-title {
  font-size: 24px; /* 与MainPage保持一致 */
  font-weight: bold;
  color: #8B4513;
  margin: 30px 0 15px 0;
  border-left: 4px solid #8B4513;
  padding-left: 15px;
}

/* 第一个章节标题不需要上边距 */
.content-section .section-title:first-child {
  margin-top: 0;
}

/* 描述文本样式：与MainPage保持一致 */
.description-text {
  font-size: 20px; /* 与MainPage保持一致 */
  color: #333;
  margin-bottom: 20px;
  text-align: justify;
  line-height: 1.8;
}

/* 列表样式 */
.content-list {
  font-size: 20px; /* 与MainPage保持一致 */
  color: #333;
  margin: 20px 0;
  padding-left: 20px;
  line-height: 1.8;
}

.content-list li {
  margin-bottom: 10px;
}

/* 子标题样式 */
.subsection-title {
  font-size: 20px;
  font-weight: bold;
  color: #8B4513;
  margin: 25px 0 10px 0;
  border-left: 3px solid #8B4513;
  padding-left: 12px;
}


/* 响应式设计：移动端适配 */
@media (max-width: 768px) {
  .main-content {
    padding: 15px; /* 与MainPage保持一致 */
    padding-top: 80px;
  }
  
  .page-title {
    font-size: 28px;
  }
  
  .content-section {
    padding: 30px 25px;
  }
  
  .back-btn {
    padding: 16px 32px;
    font-size: 17px;
    min-width: 140px;
    border-radius: 8px;
    /* 增强触摸反馈 */
    touch-action: manipulation;
    transition: all 0.2s ease;
  }
  
  .back-btn:active {
    transform: translateY(-1px) scale(0.98);
  }
  
  .description-text {
    font-size: 17px; /* 移动端字体大小 */
    line-height: 1.6;
    margin-bottom: 18px;
  }
  
  .content-list {
    font-size: 17px; /* 移动端字体大小 */
    line-height: 1.6;
    padding-left: 18px;
  }
  
  .content-list li {
    margin-bottom: 12px;
  }

  /* 移动端子标题样式 */
  .subsection-title {
    font-size: 18px;
    margin: 20px 0 8px 0;
    padding-left: 10px;
  }
  
  .section-title {
    font-size: 22px;
    margin: 25px 0 12px 0;
    padding-left: 12px;
  }
  
  .title-section {
    margin-bottom: 50px;
  }
}

/* 小屏幕手机适配 */
@media (max-width: 480px) {
  .main-content {
    padding: 12px;
  }
  
  .page-title {
    font-size: 24px;
    padding: 0 8px;
  }
  
  .back-btn {
    padding: 14px 28px;
    font-size: 16px;
    min-width: 120px;
  }
  
  .description-text {
    font-size: 16px;
    line-height: 1.5;
  }
  
  .content-list {
    font-size: 16px;
    padding-left: 16px;
  }
  
  .section-title {
    font-size: 20px;
    padding-left: 10px;
  }
  
  .subsection-title {
    font-size: 17px;
  }

}
</style>
