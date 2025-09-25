<template>
  <div class="analysis-page">
    <!-- 返回按钮 - 固定在右上角 -->
    <div class="back-button-section fade-in-up">
      <el-button @click="router.push('/main')" type="primary" class="back-btn">
        返回主页
      </el-button>
    </div>

    <!-- 主要内容区域 -->
    <div class="main-content">
      <div class="title-section fade-in-up">
        <h1 class="page-title">安全调查的具体流程</h1>
      </div>
      
        <div class="content-section fade-in-up">
          <h2 class="section-title">安全调查的具体流程</h2>
          <p class="description-text">
            加拿大学签安全调查是一个多部门协作的复杂过程，涉及IRCC、CBSA和CSIS三个主要机构的协调配合。了解这个流程有助于申请人更好地理解调查进展和预期时间。
          </p>

          <h3 class="section-title">安全调查流程详解</h3>
          <p class="description-text">
            安全调查的具体流程如下：
          </p>

          <div class="process-flow">
            <div class="flow-step">
              <div class="step-number">1</div>
              <div class="step-content">
                <h4>IRCC初步审查</h4>
                <p>VO审阅所有申请材料，对eligibility做出决定</p>
              </div>
            </div>
            
            <div class="flow-arrow">→</div>
            
            <div class="flow-step">
              <div class="step-number">2</div>
              <div class="step-content">
                <h4>转送安全调查</h4>
                <p>如无法确定admissibility，同时转送至CBSA和CSIS</p>
              </div>
            </div>
            
            <div class="flow-arrow">→</div>
            
            <div class="flow-step">
              <div class="step-number">3</div>
              <div class="step-content">
                <h4>并行调查</h4>
                <p>CBSA和CSIS同时进行各自的调查工作</p>
              </div>
            </div>
            
            <div class="flow-arrow">→</div>
            
            <div class="flow-step">
              <div class="step-number">4</div>
              <div class="step-content">
                <h4>结果汇总</h4>
                <p>CBSA提供admissibility建议，CSIS提供安全威胁评估</p>
              </div>
            </div>
            
            <div class="flow-arrow">→</div>
            
            <div class="flow-step">
              <div class="step-number">5</div>
              <div class="step-content">
                <h4>最终决定</h4>
                <p>IRCC根据调查结果做出最终签证决定</p>
              </div>
            </div>
          </div>

          <h3 class="section-title">调查方法和技术</h3>
          <p class="description-text">
            根据官方文件，NSSD和CSIS采用多种调查方法：
          </p>

          <div class="investigation-methods">
            <div class="method-category">
              <h4 class="method-title">NSSD调查方法</h4>
              <ul class="method-list">
                <li><strong>分类信息来源：</strong>CSIS简报、SIGINT安全区域</li>
                <li><strong>系统/数据库检查：</strong>STS-B/STS-S、Jane's World、LexisNexis、开源企业、独立计算机</li>
                <li><strong>其他：</strong>集中信息共享单位(CISU)、TUSCAN</li>
              </ul>
            </div>

            <div class="method-category">
              <h4 class="method-title">重叠调查方法</h4>
              <ul class="method-list">
                <li><strong>申请文件分析：</strong>身份文件、申请基础、IMM008、Schedule A、Schedule 12、面谈/官员记录</li>
                <li><strong>系统/数据库检查：</strong>NCMS、GCMS/FOSS、关注群体名单、CPIC/NCIC、INTERPOL、IBQ/ICES、IMS、MSFT存储库</li>
                <li><strong>开源调查：</strong>Google/互联网搜索、社交媒体</li>
              </ul>
            </div>

            <div class="method-category">
              <h4 class="method-title">地区调查方法</h4>
              <ul class="method-list">
                <li><strong>指纹检查：</strong>RCMP、FBI、国际、FCC-UK、FCC-AUS、FCC-NZ、FCC-USA</li>
                <li><strong>面谈：</strong>申请人面谈</li>
                <li><strong>合作伙伴联络：</strong>联络官员、美国领事馆、FBI、RCMP、当地警察、CBP、DHS</li>
              </ul>
            </div>
          </div>

          <h3 class="section-title">安全调查处理时间统计</h3>
          <p class="description-text">
            根据IRCC官方数据和论坛反馈，不同情况下的安全调查处理时间如下：
          </p>

          <div class="table-container">
            <table class="data-table">
              <thead>
                <tr>
                  <th>调查类型</th>
                  <th>平均处理时间</th>
                  <th>最长处理时间</th>
                  <th>说明</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td><strong>VIT34（国家安全）</strong></td>
                  <td>3-6个月</td>
                  <td>12-18个月</td>
                  <td>最常见的调查类型</td>
                </tr>
                <tr>
                  <td><strong>VIT35（人权侵犯）</strong></td>
                  <td>6-9个月</td>
                  <td>18-24个月</td>
                  <td>相对较少，但处理时间较长</td>
                </tr>
                <tr>
                  <td><strong>VIT37（有组织犯罪）</strong></td>
                  <td>4-8个月</td>
                  <td>15-20个月</td>
                  <td>需要国际合作调查</td>
                </tr>
                <tr>
                  <td><strong>综合调查</strong></td>
                  <td>8-12个月</td>
                  <td>24个月以上</td>
                  <td>涉及多个VIT项目</td>
                </tr>
              </tbody>
            </table>
          </div>

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
.analysis-page {
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

/* 流程步骤样式 */
.process-flow {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 30px 0;
  padding: 20px;
  background: rgba(139, 69, 19, 0.05);
  border-radius: 15px;
  overflow-x: auto;
}

.flow-step {
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 150px;
  text-align: center;
}

.step-number {
  width: 40px;
  height: 40px;
  background: #8B4513;
  color: white;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 10px;
}

.step-content h4 {
  font-size: 16px;
  color: #8B4513;
  margin: 0 0 5px 0;
  font-weight: bold;
}

.step-content p {
  font-size: 14px;
  color: #666;
  margin: 0;
  line-height: 1.4;
}

.flow-arrow {
  font-size: 24px;
  color: #8B4513;
  font-weight: bold;
  margin: 0 10px;
}

/* 调查方法样式 */
.investigation-methods {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 25px;
  margin: 30px 0;
}

.method-category {
  background: rgba(236, 222, 214, 0.3);
  padding: 25px;
  border-radius: 12px;
  border: 2px solid rgba(139, 69, 19, 0.1);
}

.method-title {
  font-size: 18px;
  font-weight: bold;
  color: #8B4513;
  margin: 0 0 15px 0;
  border-bottom: 2px solid #8B4513;
  padding-bottom: 8px;
}

.method-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.method-list li {
  margin-bottom: 12px;
  padding-left: 20px;
  position: relative;
  font-size: 16px;
  line-height: 1.5;
}

.method-list li::before {
  content: "•";
  color: #8B4513;
  font-weight: bold;
  position: absolute;
  left: 0;
}

/* 表格容器样式 */
.table-container {
  margin: 30px 0;
  overflow-x: auto;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(139, 69, 19, 0.15);
}

/* 数据表格样式 */
.data-table {
  width: 100%;
  border-collapse: collapse;
  background: white;
  border-radius: 10px;
  overflow: hidden;
  font-size: 18px;
}

.data-table thead {
  background: #8B4513;
  color: white;
}

.data-table th,
.data-table td {
  padding: 15px 20px;
  text-align: left;
  border-bottom: 1px solid #E8E0D8;
}

.data-table th {
  font-weight: bold;
  font-size: 20px;
}

.data-table tbody tr:hover {
  background: rgba(139, 69, 19, 0.05);
}

.data-table tbody tr:last-child td {
  border-bottom: none;
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
    padding: 18px 40px;
    font-size: 18px;
  }
  
  .description-text {
    font-size: 18px; /* 与MainPage保持一致 */
  }
  
  .content-list {
    font-size: 18px; /* 与MainPage保持一致 */
  }


  /* 移动端流程步骤样式 */
  .process-flow {
    flex-direction: column;
    gap: 20px;
    padding: 15px;
  }

  .flow-arrow {
    transform: rotate(90deg);
    margin: 10px 0;
  }

  .flow-step {
    min-width: auto;
    width: 100%;
  }

  .step-number {
    width: 35px;
    height: 35px;
    font-size: 16px;
  }

  .step-content h4 {
    font-size: 15px;
  }

  .step-content p {
    font-size: 13px;
  }

  /* 移动端调查方法样式 */
  .investigation-methods {
    grid-template-columns: 1fr;
    gap: 20px;
  }

  .method-category {
    padding: 20px;
  }

  .method-title {
    font-size: 16px;
  }

  .method-list li {
    font-size: 15px;
  }

  /* 移动端表格样式 */
  .data-table {
    font-size: 16px;
  }

  .data-table th,
  .data-table td {
    padding: 12px 15px;
  }

  .data-table th {
    font-size: 18px;
  }
}
</style>
