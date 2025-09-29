<template>
  <div class="example-page">
    <!-- 返回按钮 - 固定在右上角 -->
    <div class="back-button-section fade-in-up">
      <el-button @click="router.push('/main')" type="primary" class="back-btn">
        返回主页
      </el-button>
    </div>

    <!-- 主要内容区域 -->
    <div class="main-content">
      <div class="title-section fade-in-up">
        <h1 class="page-title">强制令可以结束安调吗</h1>
      </div>
      
      <div class="content-section fade-in-up">
        <h2 class="section-title">强制令与安全调查的关系</h2>
        <p class="description-text">
          强制令（Mandamus Order）是申请人可以采取的法律手段，要求法院命令IRCC在合理时间内处理申请。然而，强制令并不能直接终止安全调查，而是要求IRCC在合理时间内完成整个申请处理流程，包括必要的安全调查。
        </p>

        <h3 class="section-title">强制令的适用条件</h3>
        <p class="description-text">
          申请强制令需要满足以下条件：
        </p>
        <ul class="content-list">
          <li><strong>不合理的延迟：</strong>申请处理时间已超出正常范围，且延迟是不合理的</li>
          <li><strong>无其他有效救济途径：</strong>已尝试其他途径解决延迟问题，但未获成功</li>
          <li><strong>明确的法律义务：</strong>IRCC有法律义务在合理时间内处理申请</li>
        </ul>

        <h3 class="section-title">安全调查处理时间统计</h3>
        <p class="description-text">
          根据数据分析，不同情况下的安全调查处理时间如下：
        </p>
        <div class="table-container">
          <table class="data-table">
            <thead>
              <tr>
                <th>处理类型</th>
                <th>平均时间</th>
                <th>中位数时间</th>
                <th>说明</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><strong>整体递签到OPR</strong></td>
                <td>439天</td>
                <td>约400天</td>
                <td>包含所有申请类型</td>
              </tr>
              <tr>
                <td><strong>自然下签（无强制令）</strong></td>
                <td>457天</td>
                <td>约420天</td>
                <td>未申请强制令的案例</td>
              </tr>
              <tr>
                <td><strong>安调到OPR</strong></td>
                <td>396天</td>
                <td>约380天</td>
                <td>从安调开始计算</td>
              </tr>
              <tr>
                <td><strong>安调后自然下签</strong></td>
                <td>417天</td>
                <td>约400天</td>
                <td>安调后未申请强制令</td>
              </tr>
              <tr>
                <td><strong>强制令后下签</strong></td>
                <td>143天</td>
                <td>约130天</td>
                <td>申请强制令后的处理时间</td>
              </tr>
            </tbody>
          </table>
        </div>

        <h3 class="section-title">强制令前等待时长与成功率关系</h3>
        <p class="description-text">
          根据图表分析，强制令前等待时长与成功率存在明显关系：
        </p>
        <div class="table-container">
          <table class="data-table">
            <thead>
              <tr>
                <th>等待时长（月）</th>
                <th>成功率</th>
                <th>主要结果</th>
                <th>说明</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><strong>≤4个月</strong></td>
                <td>约20-30%</td>
                <td>等待中为主</td>
                <td>等待时间较短，多数仍在处理中</td>
              </tr>
              <tr>
                <td><strong>5-6个月</strong></td>
                <td>约40-50%</td>
                <td>成功与等待并存</td>
                <td>开始出现成功案例</td>
              </tr>
              <tr>
                <td><strong>7-8个月</strong></td>
                <td>约65-70%</td>
                <td>成功率最高</td>
                <td>最佳申请强制令时机</td>
              </tr>
              <tr>
                <td><strong>9个月</strong></td>
                <td>约50-60%</td>
                <td>成功率开始下降</td>
                <td>成功率明显降低</td>
              </tr>
              <tr>
                <td><strong>10-11个月</strong></td>
                <td>约10-20%</td>
                <td>失败率显著上升</td>
                <td>成功率急剧下降</td>
              </tr>
              <tr>
                <td><strong>≥12个月</strong></td>
                <td>约15-25%</td>
                <td>多数失败或等待</td>
                <td>成功率最低，多数仍在等待</td>
              </tr>
            </tbody>
          </table>
        </div>

        <h3 class="section-title">安调后等待强制令时长与成功率关系</h3>
        <p class="description-text">
          安调后申请强制令的时机同样影响成功率：
        </p>
        <div class="table-container">
          <table class="data-table">
            <thead>
              <tr>
                <th>安调后等待时长（月）</th>
                <th>成功率</th>
                <th>主要结果</th>
                <th>说明</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><strong>2-3个月</strong></td>
                <td>约15-25%</td>
                <td>等待中为主</td>
                <td>安调后等待时间较短</td>
              </tr>
              <tr>
                <td><strong>4-5个月</strong></td>
                <td>约40-50%</td>
                <td>成功率上升</td>
                <td>开始出现成功案例</td>
              </tr>
              <tr>
                <td><strong>6-7个月</strong></td>
                <td>约65-70%</td>
                <td>成功率最高</td>
                <td>安调后最佳申请时机</td>
              </tr>
              <tr>
                <td><strong>8个月</strong></td>
                <td>约50-60%</td>
                <td>成功率开始下降</td>
                <td>成功率明显降低</td>
              </tr>
              <tr>
                <td><strong>9-11个月</strong></td>
                <td>约10-20%</td>
                <td>失败率显著上升</td>
                <td>成功率急剧下降</td>
              </tr>
              <tr>
                <td><strong>≥12个月</strong></td>
                <td>约15-25%</td>
                <td>多数失败或等待</td>
                <td>成功率最低</td>
              </tr>
            </tbody>
          </table>
        </div>

        <h3 class="section-title">强制令成功率分析</h3>
        <p class="description-text">
          根据统计数据，强制令的成功率情况如下：
        </p>
        <ul class="content-list">
          <li><strong>自然下签：</strong>83例</li>
          <li><strong>强制令成功：</strong>146例</li>
          <li><strong>强制令失败：</strong>53例</li>
          <li><strong>等待中：</strong>699例</li>
        </ul>
        <p class="description-text">
          卡方检验结果显示，强制令对签证是否获批具有显著影响。强制令的成功率约为73.4%（146/199），远高于自然下签的比例。
        </p>

        <h3 class="section-title">关键发现与建议</h3>
        <p class="description-text">
          基于上述数据分析，我们得出以下关键发现：
        </p>
        <ul class="content-list">
          <li><strong>最佳申请时机：</strong>递签后7-8个月或安调后6-7个月是申请强制令的最佳时机，成功率可达65-70%</li>
          <li><strong>成功率急剧下降：</strong>超过9个月后，强制令成功率显著下降，10-11个月时成功率仅为10-20%</li>
          <li><strong>等待时间过长的风险：</strong>12个月以上的案例成功率最低，多数仍在等待或已失败</li>
          <li><strong>非线性关系：</strong>成功率不是简单的线性函数分布，过早或过晚申请都会影响成功率</li>
        </ul>
        <p class="description-text">
          递签后等待时间偏短时，强制令更有可能成功。但成功案例的平均等待时间都在200多天，不代表比200天更早申请强制令成功率就更大。建议申请人在合适的时机（7-8个月）考虑申请强制令。
        </p>

        <h3 class="section-title">强制令的局限性</h3>
        <p class="description-text">
          强制令虽然可以提高处理效率，但存在以下局限性：
        </p>
        <ul class="content-list">
          <li><strong>不能跳过安全调查：</strong>强制令要求IRCC在合理时间内完成处理，包括必要的安全调查</li>
          <li><strong>成功率随时间下降：</strong>等待时间过长时，强制令成功率显著降低</li>
          <li><strong>成本较高：</strong>申请强制令需要法律费用，且过程复杂</li>
          <li><strong>数据缺陷：</strong>早期获得OPR的申请人可能已离开群组，统计数据可能不完整</li>
        </ul>

        <h3 class="section-title">重要提醒</h3>
        <p class="description-text">
          强制令是最后的杀手锏，不建议轻易使用。在申请强制令前，建议：
        </p>
        <ul class="content-list">
          <li>确认申请处理时间确实超出合理范围</li>
          <li>尝试其他途径解决延迟问题</li>
          <li>咨询专业法律意见</li>
          <li>考虑申请成本和时间投入</li>
          <li>了解强制令可能带来的影响</li>
        </ul>
        <p class="description-text">
          强制令可以促使IRCC在合理时间内处理申请，但不会直接终止安全调查过程。每个申请的情况不同，法院对强制令的批准取决于具体情况。
        </p>
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
.example-page {
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

/* 表格容器样式 */
.table-container {
  margin: 30px 0;
  overflow-x: auto;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* 数据表格样式 */
.data-table {
  width: 100%;
  border-collapse: collapse;
  background: white;
  font-size: 18px;
  border-radius: 8px;
  overflow: hidden;
}

.data-table th {
  background: #8B4513;
  color: white;
  padding: 15px 12px;
  text-align: left;
  font-weight: 600;
  border: none;
}

.data-table td {
  padding: 12px;
  border-bottom: 1px solid #E8E0D8;
  color: #333;
}

.data-table tbody tr:hover {
  background-color: #F8F4F0;
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
  
  .data-table {
    font-size: 16px;
  }
  
  .data-table th,
  .data-table td {
    padding: 10px 8px;
  }
}
</style>
