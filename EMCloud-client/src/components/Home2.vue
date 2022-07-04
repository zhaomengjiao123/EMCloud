<template>
  <div class="root">
    <div class="header">
      <h1>
        <a href="index.html">
          <div class="logo">
            <img height="50px" src="../assets/imgs/logo.png" alt="XXX"/>
          </div>
        </a>
      </h1>
      <div class="page-tab">
        <div class="hoverUnderLine"></div>
        <ul class="first-menu">
          <li class="active">
            <a href="index.html">首页</a>
          </li>
          <li>
            <a href="https://www.alesmart.com/c19.shtml" target=“_blank”>智能物流配送</a>
          </li>
          <li>
            <a href="https://www.alesmart.com/c20.shtml" target=“_blank”>智能生产管控</a>
          </li>
          <li>
            <a href="https://www.alesmart.com/c21.shtml" target=“_blank”>智能检测</a>
          </li>
          <li>
            <a href="https://www.alesmart.com/c28.shtml" target=“_blank”>关于我们</a>
          </li>
          <li>
            <a href="http://localhost:8081/#/login" target=“_blank”>登录/注册</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="contetnWrapper">
      <div id="Index">
        <div class="flexslider kv_slides">
          <ul class="images">
            <li style="display:inline-block;width:33%;height:100%">
              <div id="img1" onclick="window.open('http://www.alesmart.com/x.mp4')">
                <img width="90%" src="../assets/imgs/p1.jpg" alt="智能物流配送" draggable="false" style="display: block;">
              </div>
            </li>
            <li style="display:inline-block;width:33%;height:100%">
              <div id="img2" onclick="window.open('http://www.alesmart.com/g.mp4')">
                <img width="90%" src="../assets/imgs/p2.jpg" alt="智能生产管控"/>
              </div>
            </li>
            <li style="display:inline-block;width:33%;height:100%">
              <div id="img3" onclick="window.open('http://www.alesmart.com/z1.mp4')">
                <img width="90%" src="../assets/imgs/p3.jpg" alt="智能检测"/>
              </div>
            </li>
          <!--  <li>-->
          <!--    <div class="left textAnimation" style="display:block;">-->
          <!--      <div class="font-ll">智能物流配送</div>-->
          <!--      <div class="moduleIntroduction">提高生产效率，实时了解现场工业机器人的运行状况、监控设备的工作状态、精准分析设备利用率，实现设备生产过程数据采集、状态监控、信息传递</div>-->
          <!--    </div>-->
          <!--    <div class="right ">-->
          <!--      <div class="right">-->
          <!--        <div id="img1" class="sliderImg" @mouseover="pv" @mouseout="pimg">-->
          <!--          <img class="imgAnimation_kv1" src="../assets/imgs/p1.jpg" alt="智能物流配送" draggable="false" style="display: block;">-->
          <!--        </div>-->
          <!--      </div>-->
          <!--    </div>-->
          <!--  </li>-->
          <!--  <li>-->
          <!--    <div class="left">-->
          <!--      <div class="font-ll">智能生产管控</div>-->
          <!--      <div class="moduleIntroduction">生产管控软件方面，公司开发了针对离散型、流程型、混合型等多种生产模式企业的车间物联网系统、信息采集系统、MES、ANDON、WCS、工时核算软件、智能配送系统及6D临境式交互维修培训和总控指挥系统等。</div>-->
          <!--    </div>-->
          <!--    <div class="right">-->
          <!--      <div id="img2" class="sliderImg" @mouseover="pv2" @mouseout="pimg2">-->
          <!--        <img src="../assets/imgs/p2.jpg" alt="智能生产管控"/>-->
          <!--      </div>-->
          <!--    </div>-->
          <!--  </li>-->
          <!--  <li>-->
          <!--    <div class="left">-->
          <!--      <div class="font-ll">智能检测</div>-->
          <!--      <div class="moduleIntroduction">智能检测系统方面，公司研发了发动机活塞无损高速检测装备、玻璃纤维无人质检实验室、农机关键部件检测台、中药生产在线性状参数检测技术、激光/视觉检测技术、工程机械静音试验台、压路机/平地机性能测试平台等。</div>-->
          <!--    </div>-->
          <!--    <div class="right">-->
          <!--      <div id="img3" class="sliderImg"  @mouseover="pv3" @mouseout="pimg3">-->
          <!--        <img src="../assets/imgs/p3.jpg" alt="智能检测"/>-->
          <!--      </div>-->
          <!--    </div>-->
          <!--  </li>-->
          </ul>
        </div>
        <div id="productionAndService" class="module gray">
          <ul class="productionLists">
            <li index="0">
              <div class="icons icon-khjzpj icons-one" onclick="window.open('https://www.alesmart.com/u37.shtml')"></div>
              <div class="name">软件适用</div>
              <div class="rightLine"></div>
            </li>
            <li index="1">
              <div class="icons icon-zqfxsb icons-two" onclick="window.open('https://www.alesmart.com/u35.shtml')"></div>
              <div class="name">资料下载</div>
              <div class="rightLine"></div>
            </li>
            <li index="2">
              <div class="icons icon-qzfxff icons-three" onclick="window.open('https://www.alesmart.com/u38.shtml')"></div>
              <div class="name">客户成功视频</div>
              <div class="rightLine"></div>
            </li>
            <li index="3">
              <div class="icons icon-dhjkgl icon-four" onclick="window.open('https://www.alesmart.com/p39.shtml')"></div>
              <div class="name">产品搜索</div>
            </li>
          </ul>
        </div>
        <div id="dataStatistics" class="module">
          <div class="title font-32" style="padding:45px 0 100px 0;">我们的优势</div>
          <ul class="aui-list-box">
            <li class="aui-list-grids this-card">
              <div class="aui-list-item">
                <div class="aui-list-one-bj"></div>
                <div class="aui-list-one-bg">获得国家、省级科技项目20余项</div>
              </div>
            </li>
            <li class="aui-list-grids">
              <div class="aui-list-item-two">
                <div class="aui-list-one-bj"></div>
                <div class="aui-list-one-bg">获得授权发明专利20余项，软件著作权14项</div>
              </div>
            </li>
            <li class="aui-list-grids">
              <div class="aui-list-item-three">
                <div class="aui-list-one-bj"></div>
                <div class="aui-list-one-bg">能够提供高效、稳定的系统技术服务</div>
              </div>
            </li>
            <li class="aui-list-grids">
              <div class="aui-list-item-four">
                <div class="aui-list-one-bj"></div>
                <div class="aui-list-one-bg">为多家知名企业提供核心装备支撑</div>
              </div>
            </li>
            <li class="aui-list-grids">
              <div class="aui-list-item-five">
                <div class="aui-list-one-bj"></div>
                <div class="aui-list-one-bg">长期致力于数字化高端智能装备系统的研发制造</div>
              </div>
            </li>
          </ul>
        </div>
        <div id="partner" class="module">
          <div class="title font-32" style="padding:45px 0 0 0;">客户和合作伙伴</div>
          <div class="content">
            <img src="../assets/imgs/partner.png">
          </div>
        </div>
      </div>
    </div>
    <div class="footerWrapper">
      <br><br>
      <div class="title font-32" style="padding:45px 0 0 0;">联系我们</div>
      <div style="font-size:17px;color:#4d4d4d;text-align:center;padding:10px 0 50px 0;">亚历山大与您共创辉煌</div>
      <div class="footer">
        <div class="top">
          <div class="item">
            <ul class="cursor_default">
              <li>
                <a>
                  <span class="footerIcons footerPhone"></span>
                  <span>山东亚历山大智能科技有限公司</span>
                </a>
              </li>
              <li>
                <a>
                  <span class="footerIcons footerAfterSale"></span>
                  <span>13065039691</span>
                </a>
              </li>
              <li>
                <a>
                  <span class="footerIcons footerBusiness"></span>
                  <span>xbc@alesmart.com</span>
                </a>
              </li>
              <li>
                <a>
                  <span class="footerIcons footerMedia"></span>
                  <span>山东省济南市历下区经十路13777号中润世纪城9号楼102</span>
                </a>
              </li>
            </ul>
          </div>
          <div class="adcodeArea">
            <div class="icon_adcode">
              <img class="full_height" src="../assets/imgs/weixin.jpg" alt="抖音账号"/>
            </div>
          </div>
        </div>
      </div>
      <div class="tipmsgArea">
        <div class="content">
          <div class="name">
            <em>
              <img src="../assets/imgs/icon-ga.png" width="20" height="20" alt="">备案号：鲁ICP备17053761号-1
            </em>
          </div>
        </div>
      </div>
    </div>
    <div class="mask">
      <div class="layer"></div>
      <div class="content">
        <img src="../assets/imgs/maskLayer.png" alt="低于ie8提示"/>
        <a class="google" href="https://www.google.cn/intl/zh-CN/chrome/browser/desktop/" target=_blank>谷歌 Chrome</a>
        <a class="firefox" href="http://www.firefox.com.cn/" target=_blank>火狐 Firefox</a>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
$('.aui-list-grids').click(function(e) {

  $(this).addClass('this-card').siblings().removeClass('this-card');

  $('#type-amount').html($(this).find('.cardAmount').html());

})

export default {
  name: "Home2",
  data() {
    return {
      flag: 1
    }
  },
  methods: {
    pv() {
      if(this.flag==1) {
        document.all.img1.innerHTML="<video width='397' height='423' controls autoplay='autoplay'><source src='http://www.alesmart.com/x.mp4' type='video/mp4'></video>";
        this.flag=0;
      }
    },
    pimg() {
      this.flag=1;
      document.all.img1.innerHTML="<img src='../assets/imgs/p1.jpg'>";
    },
    pv2() {
      if(this.flag==1) {
        document.all.img2.innerHTML="<video width='397' height='423' controls autoplay='autoplay'><source src='http://www.alesmart.com/g.mp4' type='video/mp4'></video>";
        this.flag=0;
      }
    },
    pimg2() {
      this.flag=1;
      document.all.img2.innerHTML="<img src='../assets/imgs/p2.jpg'>";
    },
    pv3() {
      if(this.flag==1) {
        document.all.img3.innerHTML="<video width='397' height='423' controls autoplay='autoplay'><source src='http://www.alesmart.com/z1.mp4' type='video/mp4'></video>";
        this.flag=0;
      }
    },
    pimg3() {
      this.flag=1;
      document.all.img3.innerHTML="<img src='../assets/imgs/p3.jpg'>";
    }
  }
}
</script>

<style>

@import '../assets/css/home.css';
@import '../assets/css/base.css';

</style>
