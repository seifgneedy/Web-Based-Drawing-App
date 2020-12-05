<template>
  <div class="wrapper">
    <div class="toolbar">
      <div v-show="this.isUploadShowing">
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
        <button v-on:click="submitFile()">Submit</button>
      </div>
      <a href="#" id="open" @click="showUpload"
        ><img src="../assets/open.png"
      /></a>
      <a href="#" id="Line" @click="ChangeTool('Line')"
        ><img src="../assets/line.png"
      /></a>
      <a href="#" id="Rectangle" @click="ChangeTool('Rectangle')"
        ><img src="../assets/rectangle.png"
      /></a>
      <a href="#" id="Square" @click="ChangeTool('Square')"
        ><img src="../assets/square.png"
      /></a>
      <a href="#" id="Circle" @click="ChangeTool('Circle')"
        ><img src="../assets/circle.png"
      /></a>
      <a href="#" id="Ellipse" @click="ChangeTool('Ellipse')"
        ><img src="../assets/ellipse.png"
      /></a>
      <a href="#" id="Triangle" @click="ChangeTool('Triangle')"
        ><img src="../assets/Triangle.png"
      /></a>
      <button @click="downloadJSON">Save As JSON</button>
      <button @click="downloadXML">Save As XML</button>
    </div>
    <br />

    <div class="toolbar">
      <a id="stroke" @click="changedrawtype('stroke')">stroke</a>
      <a id="fill" @click="changedrawtype('fill')">fill</a>
     
      <div class="col_3">
        <form>
          <span style="color:white;">Pick Color </span>
          <input type="color" id="color" />
        </form>
      </div>
      <div class="col_3" id="line">
        <span style="color:white;"> || Line width </span>
        <select id="selectLineWidth"></select>
         <div class="col_3"><input type="button" value="Undo" style="background-color:yellow; " @click="Undo"></div>
      <div class="col_3"><input type="button"  value="Redo" style="background-color:red;"  @click="Redo"></div>
      </div>
      <br /><br />
    </div>
    <br />

    <canvas id="my-canvas" width="900" height="500"></canvas>
    <div id="img-data-div">
      <a href="#" id="img-file" download="image.png">download image</a>
    </div>
  </div>
</template>

<style>
button {
  color: white;
  margin: 10px;
  background-color: #181414e0;
}
#color {
  background-color: #181414e0;
}
.wrapper {
  max-width: 900px;
  margin: auto;
  font-family: "Arial";
}

.toolbar {
  width: 100%;
  background-color: #181414e0;
  overflow: auto;
}
.clearfix {
  content: "";
  display: table;
  clear: both;
  margin: 0;
}
.col_3 {
  margin: 0;
  padding: 4px;
  float: left;
}
.button.top {
  background-color: blue;
}
.toolbar a {
  float: left;
  width: 10%;
  text-align: center;
  padding: 6px 5px;
  transition: all 0.5s ease;
  color: white;
}

/* Change color on hover */
.toolbar a:hover {
  background-color: #000;
}

/* Change color on selected icon */
.selected {
  background-color: #000;
}

#my-canvas {
  border: 3px solid #000000;
}

#img-data-div {
  width: 100%;
  max-width: 900px;
  height: 200px;
}

/* Resize image to container */
.toolbar a img {
  max-width: 100%;
  height: auto;
}
</style>

<script>
import { AXIOS } from "../http-common";
export default {
  components: {},
  data() {
    return {
      canvas: null,
      ctx: null,
      colorInput: null,
      lineWidthInput: null,
      file: '',
      isUploadShowing: false,
      // Stores previously drawn image data to restore after
      // new drawings are added
      savedImageData: null,
      dragging: false,
      strokeColor: "black",
      fillColor: "black",
      line_Width: 4,
      TriangleSides: 3,
      drawtype: "stroke",
      currentTool: "line",
      canvasWidth: 900,
      canvasHeight: 500,
      canvasSizeData: "",
      TrianglePoints: new Array(),
      savedTrianglePoints: new Array(),
      shape: {},
      shapes:[],
      shapeBoundingBox: {
        left: 0,
        top: 0,
        width: 0,
        height: 0
      },
      mousedown: {
        x: 0,
        y: 0
      },
      loc: {
        x: 0,
        y: 0
      }
    };
  },
  mounted() {
    this.canvas = document.getElementById("my-canvas");
    this.ctx = this.canvas.getContext("2d");
    this.reset();
    this.ctx.strokeStyle = this.strokeColor;
    this.ctx.lineWidth = this.line_Width;

    this.canvas.addEventListener("mousedown", this.ReactToMouseDown);
    this.canvas.addEventListener("mousemove", this.ReactToMouseMove);
    this.canvas.addEventListener("mouseup", this.ReactToMouseUp);

    this.colorInput = document.getElementById("color");

    this.colorInput.addEventListener("input", this.ReactToColor);

    document.getElementById("line").className = "selected";
    document.getElementById("stroke").className = "selected";

    this.lineWidthInput = document.getElementById("selectLineWidth");
    let contents;
    for (let i = 1; i <= 100; i++) {
      contents += "<option>" + i + "</option>";
    }
    this.lineWidthInput.innerHTML = contents;
    this.lineWidthInput.value = 4;
    this.lineWidthInput.addEventListener("change", this.ReactToLineWidth);
  },
  methods: {
    ChangeTool: function(toolClicked) {
      document.getElementById("open").className = "";
      document.getElementById("Line").className = "";
      document.getElementById("Rectangle").className = "";
      document.getElementById("Circle").className = "";
      document.getElementById("Ellipse").className = "";
      document.getElementById("Triangle").className = "";
      document.getElementById("Square").className = "";

      document.getElementById(toolClicked).className = "selected";

      this.currentTool = toolClicked;
    },
    GetMousePosition: function(x, y) {
      this.canvasSizeData = this.canvas.getBoundingClientRect();
      return {
        x:
          (x - this.canvasSizeData.left) *
          (this.canvas.width / this.canvasSizeData.width),
        y:
          (y - this.canvasSizeData.top) *
          (this.canvas.height / this.canvasSizeData.height)
      };
    },
    SaveCanvasImage: function() {
      // Save image
      this.savedImageData = this.ctx.getImageData(
        0,
        0,
        this.canvas.width,
        this.canvas.height
      );
    },
    RedrawCanvasImage: function() {
      // Restore image
      this.ctx.putImageData(this.savedImageData, 0, 0);
    },
    UpdateRubberbandSizeData: function() {
      // Height & width are the difference between were clicked
      // and current mouse position
      this.shapeBoundingBox.width = Math.abs(this.loc.x - this.mousedown.x);
      this.shapeBoundingBox.height = Math.abs(this.loc.y - this.mousedown.y);

      // If mouse is below where mouse was clicked originally
      if (this.loc.x > this.mousedown.x) {
        // Store mousedown because it is farthest left
        this.shapeBoundingBox.left = this.mousedown.x;
      } else {
        // Store mouse location because it is most left
        this.shapeBoundingBox.left = this.loc.x;
      }

      // If mouse location is below where clicked originally
      if (this.loc.y > this.mousedown.y) {
        // Store mousedown because it is closer to the top
        // of the canvas
        this.shapeBoundingBox.top = this.mousedown.y;
      } else {
        // Otherwise store mouse position
        this.shapeBoundingBox.top = this.loc.y;
      }
    },
    getAngleUsingXAndY: function(mouselocX, mouselocY) {
      let adjacent = this.mousedown.x - mouselocX;
      let opposite = this.mousedown.y - mouselocY;

      return this.radiansToDegrees(Math.atan2(opposite, adjacent));
    },
    radiansToDegrees: function(rad) {
      if (rad < 0) {
        // Correct the bottom error by adding the negative
        // angle to 360 to get the correct result around
        // the whole circle
        return (360.0 + rad * (180 / Math.PI)).toFixed(2);
      } else {
        return (rad * (180 / Math.PI)).toFixed(2);
      }
    },
    degreesToRadians: function(degrees) {
      return degrees * (Math.PI / 180);
    },
    getTrianglePoints() {
      // Get angle in radians based on x & y of mouse location
      let angle = this.degreesToRadians(
        this.getAngleUsingXAndY(this.loc.x, this.loc.y)
      );

      // X & Y for the X & Y point representing the radius is equal to
      // the X & Y of the bounding rubberband box
      let radiusX = this.shapeBoundingBox.width;
      let radiusY = this.shapeBoundingBox.height;
      // Stores all points in the polygon

      // Then I can use the known angle and adjacent side length
      // to find the X = mouseLoc.x + radiusX * Sin(angle)
      // You find the Y = mouseLoc.y + radiusY * Cos(angle)

      // 2 * PI equals 360 degrees
      // Divide 360 into parts based on how many triangle
      // sides you want
      for (let i = 0; i < this.TriangleSides; i++) {
        this.TrianglePoints.push({
          x: this.loc.x + radiusX * Math.sin(angle),
          y: this.loc.y - radiusY * Math.cos(angle)
        });

        // 2 * PI equals 360 degrees
        // Divide 360 into parts based on how many triangle
        // sides you want
        angle += (2 * Math.PI) / this.TriangleSides;
      }
    },
    drawRubberbandShape: function() {
      this.shape["type"] = this.currentTool;
      this.shape["color"] = this.strokeColor;
      this.shape["color"] = this.fillColor;
      this.shape["lineWidth"] = this.line_Width;
      this.shape["filled"] = this.drawtype === "fill" ? true : false;
      this.shape["x"] = this.mousedown.x;
      this.shape["y"] = this.mousedown.y;
      if (this.currentTool === "Line") {
        this.shape["x2"] = this.loc.x;
        this.shape["y2"] = this.loc.y;
      } else if (
        this.currentTool === "Square" ||
        this.currentTool === "Rectangle"
      ) {
        this.shape["x"] = this.shapeBoundingBox.left;
        this.shape["y"] = this.shapeBoundingBox.top;
        this.shape["width"] = this.shapeBoundingBox.width;
        if (this.currentTool === "Rectangle")
          this.shape["length"] = this.shapeBoundingBox.height;
      } else if (this.currentTool === "Circle") {
        this.shape["radius"] = this.shapeBoundingBox.width;
      } else if (this.currentTool === "Ellipse") {
        this.shape["a"] = this.shapeBoundingBox.width;
        this.shape["b"] = this.shapeBoundingBox.height;
      } else {
        this.getTrianglePoints();
        this.shape["x"] = this.TrianglePoints[0].x;
        this.shape["y"] = this.TrianglePoints[0].y;
        this.shape["x2"] = this.TrianglePoints[1].x;
        this.shape["y2"] = this.TrianglePoints[1].y;
        this.shape["x3"] = this.TrianglePoints[2].x;
        this.shape["y3"] = this.TrianglePoints[2].y;
        this.TrianglePoints = new Array();
      }
      this.drawShape(this.shape);
    },
    UpdateRubberbandOnMove: function() {
      // Stores changing height, width, x & y position of most
      // top left point being either the click or mouse location
      this.UpdateRubberbandSizeData();

      // Redraw the shape
      this.drawRubberbandShape();
    },
    ReactToMouseDown(e) {
      this.canvas.style.cursor = "crosshair";
      this.loc = this.GetMousePosition(e.clientX, e.clientY);
      this.SaveCanvasImage();

      this.mousedown.x = this.loc.x;
      this.mousedown.y = this.loc.y;

      this.dragging = true;
    },
    ReactToMouseMove(e) {
      this.canvas.style.cursor = "crosshair";
      this.loc = this.GetMousePosition(e.clientX, e.clientY);

      if (this.dragging) {
        this.shape = {};
        this.RedrawCanvasImage();
        this.UpdateRubberbandOnMove();
      }
    },
   async ReactToMouseUp(e) {
      this.canvas.style.cursor = "default";
      this.loc = this.GetMousePosition(e.clientX, e.clientY);
      if (this.mousedown.x != this.loc.x || this.mousedown.y != this.loc.y) {
        //this.RedrawCanvasImage();
        this.UpdateRubberbandOnMove();
       await this.sendNewShape();
        this.drawShapesFromServer();
      }
      this.dragging = false;
    },
    SaveImage: function() {
      // Get a reference to the link element
      var imageFile = document.getElementById("img-file");
      // Set that you want to download the image when link is clicked
      imageFile.setAttribute("download", "image.png");
      // Reference the image in canvas for download
      imageFile.setAttribute("href", this.canvas.toDataURL());
    },
    changedrawtype(type) {
      document.getElementById("stroke").className = "";
      document.getElementById("fill").className = "";
      document.getElementById(type).className = "selected";
      ///
      this.lineWidthInput.value = 4;
      this.line_Width = 4;
      ///
      this.drawtype = type;
    },
    ReactToColor: function() {
      this.strokeColor = this.colorInput.value;
      this.fillColor = this.colorInput.value;
    },
    ReactToLineWidth: function() {
      this.line_Width = this.lineWidthInput.value;
    },
    drawShape: function(s) {
      this.ctx.fillStyle = s["color"];
      this.ctx.strokeStyle = s["color"];
      this.ctx.lineWidth = s["lineWidth"];
      switch (s["type"]) {
        case "Line":
          this.drawLine(s);
          break;
        case "Rectangle":
          this.drawRectangle(s);
          break;
        case "Square":
          this.drawSquare(s);
          break;
        case "Circle":
          this.drawCircle(s);
          break;
        case "Ellipse":
          this.drawEllipse(s);
          break;
        case "Triangle":
          this.drawTriangle(s);
          break;
      }
    },
    drawLine: function(s) {
      this.ctx.beginPath();
      this.ctx.moveTo(s["x"], s["y"]);
      this.ctx.lineTo(s["x2"], s["y2"]);
      this.ctx.stroke();
      this.ctx.closePath();
    },
    drawRectangle: function(s) {
      if (s["filled"]) {
        this.ctx.fillRect(s["x"], s["y"], s["width"], s["length"]);
      } else {
        this.ctx.strokeRect(s["x"], s["y"], s["width"], s["length"]);
      }
    },
    drawSquare: function(s) {
      if (s["filled"]) {
        this.ctx.fillRect(s["x"], s["y"], s["width"], s["width"]);
      } else {
        this.ctx.strokeRect(s["x"], s["y"], s["width"], s["width"]);
      }
    },
    drawCircle: function(s) {
      this.ctx.beginPath();
      this.ctx.arc(s["x"], s["y"], s["radius"], 0, Math.PI * 2);
      s["filled"] ? this.ctx.fill() : this.ctx.stroke();
      this.ctx.closePath();
    },
    drawEllipse: function(s) {
      this.ctx.beginPath();
      this.ctx.ellipse(s["x"], s["y"], s["a"], s["b"], 0, 0, Math.PI * 2);
      s["filled"] ? this.ctx.fill() : this.ctx.stroke();
      this.ctx.closePath();
    },
    drawTriangle: function(s) {
      this.ctx.beginPath();
      this.ctx.moveTo(s["x"], s["y"]);
      this.ctx.lineTo(s["x2"], s["y2"]);
      this.ctx.lineTo(s["x3"], s["y3"]);
      this.ctx.closePath();
      s["filled"] ? this.ctx.fill() : this.ctx.stroke();
    },
    drawShapes:function(shapes){
      this.ctx.save();
      for (let s=0;s<shapes.length;s++) {
        if(shapes[s]!=null)
            this.drawShape(shapes[s]);
      }
      this.ctx.restore();
    },
    sendNewShape: async function() {
      await AXIOS.post("add", this.shape);
    },
    drawShapesFromServer: async function() {
       await AXIOS.get("/shapes").then(response=>{
           this.shapes=response.data;
       }).catch(error=>{
           console.log(error);
       });
      this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
    //   this.drawShape(this.shape);
      this.drawShapes(this.shapes);
    },
    reset:function(){
        AXIOS.get("/reset");
        this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
    },
    downloadXML() {
      const FileDownload = require("js-file-download");
      AXIOS.get("/downloadXML").then(response => {
        FileDownload(response.data, "saved.xml");
      });
    },
    downloadJSON() {
      const FileDownload = require("js-file-download");
      AXIOS.get("/downloadJSON").then(response => {
        FileDownload(JSON.stringify(response.data), "saved.json");
      });
    },
    submitFile(){
      let formData = new FormData();
      formData.append('file', this.file);
      AXIOS.post('/upload',
          formData,
          {
          headers: {
              'Content-Type': 'multipart/form-data'
          }
          }
      ).then(response => {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        this.drawShapes(response.data);
      })
      .catch();
      this.isUploadShowing = false;
    },
    handleFileUpload(){
      this.file = this.$refs.file.files[0];
    },
    showUpload(){
      this.isUploadShowing = true;
    },Undo(){
        AXIOS.get("/undo").then(response=>{
           this.shapes=response.data;
           this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);
            this.drawShapes(this.shapes);
       }).catch(error=>{
           console.log(error);
       });
    },
    Redo(){
        AXIOS.get("/redo").then(response=>{
           this.shapes=response.data;
            this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
            this.drawShapes(this.shapes);
       }).catch(error=>{
           console.log(error);
       });
    }
  }
};
</script>
//Not much but honest work
