<template>
  <div id="agvMapDiv" class="scroll-container" >
    <div class="btns">
      <el-button v-on:click="initMap" size="mini">加载</el-button>
      <el-button v-on:click="mapIn" size="mini">放大</el-button>
      <el-button v-on:click="mapOut" size="mini">缩小</el-button>
      <button v-on:click="reset" hidden>还原</button>
    </div>

    <canvas id="agvCap" width="100" height="100" style="border: 0px solid #00ff00; position: absolute; left: 0; top: 25px; z-index: 0;"></canvas>
    <canvas id="agvMap" width="100" height="100" style="border: 0px solid #FF0000; position: absolute; left: 0; top: 25px; z-index: 1;"></canvas>
    <canvas id="agvRoute" width="100" height="100" style="border: 0px solid #FF0000; position: absolute; left: 0; top: 25px; z-index: 2;"></canvas>
  </div>
</template>

<script>
import mapInfo from "../../assets/map/default.json"

export default {
  name: "EquipmentRuningMap",
  data(){
    return{
      name: 20,
      mapData:null,
      mapWidth:0,
      mapHeight:0,
      mapProportion:1.0,
      mapCoefficient:0.05,
    }
  },
  created() {
    this.initMap();
  },
  methods: {
    initMap: function () {
      console.log("......");
      //axios.get("http://127.0.0.1:9090/home/getAgvMap?agvID=ale_robotagv02").then(res=>{
       this.mapData = mapInfo;
      console.log("MapData；",this.mapData)
       this.mapWidth = (this.mapData.header.maxPos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution;
       this.mapHeight = (this.mapData.header.maxPos.y - this.mapData.header.minPos.y) / this.mapData.header.resolution;
        this.mapProportion = 1.0;
        this.mapCoefficient = 0.05;
        /*---------------地图------------------*/
        let agvMap = document.getElementById("agvMap");
        agvMap.width = this.mapWidth;
        agvMap.height = this.mapHeight;
        let ctxMap = agvMap.getContext("2d");
        ctxMap.fillStyle="#000000";
        for (let i=0;i<this.mapData.normalPosList.length;i++)
        {
          let pos = this.mapData.normalPosList[i];
          let x = (pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution;
          let y = (pos.y - this.mapData.header.minPos.y) /  this.mapData.header.resolution;
          ctxMap.fillRect(x,y,1,1);
        }

        /*--------------cap点--------------------*/
      let agvCap = document.getElementById("agvCap");
        agvCap.width = this.mapWidth;
        agvCap.height = this.mapHeight;
      let ctxCap = agvCap.getContext("2d");
        ctxCap.fillStyle="#0000FF";

        for (let i=0;i<this.mapData.advancedPointList.length;i++) {
          let pos = this.mapData.advancedPointList[i].pos;
          let x = (pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution;
          let y = (pos.y - this.mapData.header.minPos.y) /  this.mapData.header.resolution;
          ctxCap.beginPath();
          ctxCap.arc(x,y,3,0,2*Math.PI);
          ctxCap.fill();
        }

        /*--------------route点--------------------*/
        var agvRoute = document.getElementById("agvRoute");
        agvRoute.width = this.mapWidth;
        agvRoute.height = this.mapHeight;
        var ctxRoute = agvRoute.getContext("2d");
        ctxRoute.strokeStyle="#0000FF";
        for (var i = 0; i < this.mapData.advancedCurveList.length; i++) {
          var startPos = this.mapData.advancedCurveList[i].startPos;
          var endPos = this.mapData.advancedCurveList[i].endPos;
          var controlPos1 = this.mapData.advancedCurveList[i].controlPos1;
          var controlPos2 = this.mapData.advancedCurveList[i].controlPos2;

          var startx = (startPos.pos.x - this.mapData.header.minPos.x)/ this.mapData.header.resolution;
          var starty = (startPos.pos.y - this.mapData.header.minPos.y)/ this.mapData.header.resolution;
          var p1x = (controlPos1.x - this.mapData.header.minPos.x) / this.mapData.header.resolution;
          var p1y = (controlPos1.y - this.mapData.header.minPos.y) / this.mapData.header.resolution;
          var p2x = (controlPos2.x - this.mapData.header.minPos.x) / this.mapData.header.resolution;
          var p2y = (controlPos2.y - this.mapData.header.minPos.y) / this.mapData.header.resolution;
          var endx = (endPos.pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution;
          var endy = (endPos.pos.y - this.mapData.header.minPos.y) / this.mapData.header.resolution;
          //console.log("startx:"+startx+",starty:"+starty+",p1x:"+p1x+",p1y:"+p1y+",p2x:"+p2x+",p2y:"+p2y+",endx:"+endx+",endy:"+endy);
          ctxRoute.beginPath();
          ctxRoute.moveTo(startx,starty);
          ctxRoute.bezierCurveTo(p1x,p1y,p2x,p2y,endx,endy);
          ctxRoute.stroke();
        }

    },
    mapIn:function(){
      /*---------------地图------------------*/
      var agvMap = document.getElementById("agvMap");
      this.mapProportion = this.mapProportion + this.mapCoefficient;
      agvMap.width = this.mapWidth * this.mapProportion;
      agvMap.height = this.mapHeight * this.mapProportion;
      var ctxMap = agvMap.getContext("2d");
      ctxMap.fillStyle="#000000";
      for (var i=0;i<this.mapData.normalPosList.length;i++)
      {
        var pos = this.mapData.normalPosList[i];
        var x = ((pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var y = ((pos.y - this.mapData.header.minPos.y) /  this.mapData.header.resolution) * this.mapProportion;
        ctxMap.fillRect(x,y,1,1);
      }
      /*--------------cap点--------------------*/
      var agvCap = document.getElementById("agvCap");
      agvCap.width = this.mapWidth * this.mapProportion;
      agvCap.height = this.mapHeight * this.mapProportion;
      var ctxCap = agvCap.getContext("2d");
      ctxCap.fillStyle="#0000FF";
      for (var i=0;i<this.mapData.advancedPointList.length;i++) {
        var pos = this.mapData.advancedPointList[i].pos;
        var x = ((pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var y = ((pos.y - this.mapData.header.minPos.y) /  this.mapData.header.resolution) * this.mapProportion;
        ctxCap.beginPath();
        ctxCap.arc(x,y,3,0,2*Math.PI);
        ctxCap.fill();
      }
      /*--------------route点--------------------*/
      var agvRoute = document.getElementById("agvRoute");
      agvRoute.width = this.mapWidth * this.mapProportion;
      agvRoute.height = this.mapHeight * this.mapProportion;
      var ctxRoute = agvRoute.getContext("2d");
      ctxRoute.strokeStyle="#0000FF";
      for (var i = 0; i < this.mapData.advancedCurveList.length; i++) {
        var startPos = this.mapData.advancedCurveList[i].startPos;
        var endPos = this.mapData.advancedCurveList[i].endPos;
        var controlPos1 = this.mapData.advancedCurveList[i].controlPos1;
        var controlPos2 = this.mapData.advancedCurveList[i].controlPos2;

        var startx = ((startPos.pos.x - this.mapData.header.minPos.x)/ this.mapData.header.resolution) * this.mapProportion;
        var starty = ((startPos.pos.y - this.mapData.header.minPos.y)/ this.mapData.header.resolution) * this.mapProportion;
        var p1x = ((controlPos1.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var p1y = ((controlPos1.y - this.mapData.header.minPos.y) / this.mapData.header.resolution) * this.mapProportion;
        var p2x = ((controlPos2.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var p2y = ((controlPos2.y - this.mapData.header.minPos.y) / this.mapData.header.resolution) * this.mapProportion;
        var endx = ((endPos.pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var endy = ((endPos.pos.y - this.mapData.header.minPos.y) / this.mapData.header.resolution) * this.mapProportion;
        //console.log("startx:"+startx+",starty:"+starty+",p1x:"+p1x+",p1y:"+p1y+",p2x:"+p2x+",p2y:"+p2y+",endx:"+endx+",endy:"+endy);
        ctxRoute.beginPath();
        ctxRoute.moveTo(startx,starty);
        ctxRoute.bezierCurveTo(p1x,p1y,p2x,p2y,endx,endy);
        ctxRoute.stroke();
      }
    },
    mapOut:function(){
      var agvMap = document.getElementById("agvMap");
      this.mapProportion = this.mapProportion - this.mapCoefficient;
      agvMap.width = this.mapWidth * this.mapProportion;
      agvMap.height = this.mapHeight * this.mapProportion;
      var ctxMap = agvMap.getContext("2d");
      ctxMap.fillStyle="#000000";
      for (var i=0;i<this.mapData.normalPosList.length;i++)
      {
        var pos = this.mapData.normalPosList[i];
        var x = ((pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var y = ((pos.y - this.mapData.header.minPos.y) /  this.mapData.header.resolution) * this.mapProportion;
        ctxMap.fillRect(x,y,1,1);
      }
      /*--------------cap点--------------------*/
      var agvCap = document.getElementById("agvCap");
      agvCap.width = this.mapWidth * this.mapProportion;
      agvCap.height = this.mapHeight * this.mapProportion;
      var ctxCap = agvCap.getContext("2d");
      ctxCap.fillStyle="#0000FF";
      for (var i=0;i<this.mapData.advancedPointList.length;i++) {
        var pos = this.mapData.advancedPointList[i].pos;
        var x = ((pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var y = ((pos.y - this.mapData.header.minPos.y) /  this.mapData.header.resolution) * this.mapProportion;
        ctxCap.beginPath();
        ctxCap.arc(x,y,3,0,2*Math.PI);
        ctxCap.fill();
      }
      /*--------------route点--------------------*/
      var agvRoute = document.getElementById("agvRoute");
      agvRoute.width = this.mapWidth * this.mapProportion;
      agvRoute.height = this.mapHeight * this.mapProportion;
      var ctxRoute = agvRoute.getContext("2d");
      ctxRoute.strokeStyle="#0000FF";
      for (var i = 0; i < this.mapData.advancedCurveList.length; i++) {
        var startPos = this.mapData.advancedCurveList[i].startPos;
        var endPos = this.mapData.advancedCurveList[i].endPos;
        var controlPos1 = this.mapData.advancedCurveList[i].controlPos1;
        var controlPos2 = this.mapData.advancedCurveList[i].controlPos2;

        var startx = ((startPos.pos.x - this.mapData.header.minPos.x)/ this.mapData.header.resolution) * this.mapProportion;
        var starty = ((startPos.pos.y - this.mapData.header.minPos.y)/ this.mapData.header.resolution) * this.mapProportion;
        var p1x = ((controlPos1.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var p1y = ((controlPos1.y - this.mapData.header.minPos.y) / this.mapData.header.resolution) * this.mapProportion;
        var p2x = ((controlPos2.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var p2y = ((controlPos2.y - this.mapData.header.minPos.y) / this.mapData.header.resolution) * this.mapProportion;
        var endx = ((endPos.pos.x - this.mapData.header.minPos.x) / this.mapData.header.resolution) * this.mapProportion;
        var endy = ((endPos.pos.y - this.mapData.header.minPos.y) / this.mapData.header.resolution) * this.mapProportion;
        //console.log("startx:"+startx+",starty:"+starty+",p1x:"+p1x+",p1y:"+p1y+",p2x:"+p2x+",p2y:"+p2y+",endx:"+endx+",endy:"+endy);
        ctxRoute.beginPath();
        ctxRoute.moveTo(startx,starty);
        ctxRoute.bezierCurveTo(p1x,p1y,p2x,p2y,endx,endy);
        ctxRoute.stroke();
      }
    },
    reset:function(){
      console.log("reset");
      var agvCap = document.getElementById("agvCap");
      agvCap.width = 250;
      agvCap.height = 250;
      var ctx=agvCap.getContext("2d");
      ctx.fillStyle="#ff0000";
      ctx.beginPath();
      ctx.moveTo(20,20);
      ctx.bezierCurveTo(20,100,200,100,200,20);
      ctx.strokeStyle = "”"
      ctx.stroke();
    }
  },


}


</script>

<style lang="scss" scoped>

.scroll-container{
  border: 1px solid #000;
  //width: 1000px;
  width: 100%;
  //height: 600px;
  height: 100%;
  overflow: auto;
  text-align: center;
  position: relative;
}

</style>
