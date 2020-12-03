<template>
  <div class="wrapper">
    <div class="toolbar">
      <a class="selected" href="#" id="open" @click="OpenImage()"
        ><img src="./open.png"
      /></a>
      <a href="#" id="save" @click="SaveImage()"><img src="./save.png"/></a>
      <a href="#" id="line" @click="ChangeTool('line')"
        ><img src="./line.png"
      /></a>
      <a href="#" id="rectangle" @click="ChangeTool('rectangle')"
        ><img src="./rectangle.png"
      /></a>
      <a href="#" id="square" @click="ChangeTool('square')"
        ><img src="./square.png"
      /></a>
      <a href="#" id="circle" @click="ChangeTool('circle')"
        ><img src="./circle.png"
      /></a>
      <a href="#" id="ellipse" @click="ChangeTool('ellipse')"
        ><img src="./ellipse.png"
      /></a>
      <a href="#" id="Triangle" @click="ChangeTool('Triangle')"
        ><img src="./Triangle.png"
      /></a>
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
import axios from "axios";
export default {
  data() {
    return {
      canvas: null,
      // Context provides functions used for drawing and
      // working with Canvas
      ctx: null,
      colorInput: null,
      lineWidthInput: null,
      // Stores previously drawn image data to restore after
      // new drawings are added
      savedImageData: null,
      // Stores whether I'm currently dragging the mouse
      dragging: false,
      strokeColor: "black",
      fillColor: "black",
      line_Width: 4,
      TriangleSides: 3,
      drawtype: "stroke",
      // Tool currently using
      currentTool: "line",
      canvasWidth: 900,
      canvasHeight: 500,
      canvasSizeData: "",
      TrianglePoints: new Array(),
      savedTrianglePoints: new Array(),
      shape: {},
      shapeBoundingBox: {
        left: 0,
        top: 0,
        width: 0,
        height: 0,
      },
      mousedown: {
        x: 0,
        y: 0,
      },
      loc: {
        x: 0,
        y: 0,
      },
    };
  },
  mounted() {
    this.canvas = document.getElementById("my-canvas");
    // Get methods for manipulating the canvas
    this.ctx = this.canvas.getContext("2d");
    this.ctx.strokeStyle = this.strokeColor;
    this.ctx.lineWidth = this.line_Width;
    // Execute ReactToMouseDown when the mouse is clicked
    this.canvas.addEventListener("mousedown", this.ReactToMouseDown);
    // Execute ReactToMouseMove when the mouse is clicked
    this.canvas.addEventListener("mousemove", this.ReactToMouseMove);
    // Execute ReactToMouseUp when the mouse is clicked
    this.canvas.addEventListener("mouseup", this.ReactToMouseUp);
    //document.addEventListener('DOMContentLoaded', this.setupCanvas);

    this.colorInput = document.getElementById("color");

    this.colorInput.addEventListener("input", this.ReactToColor);

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
      document.getElementById("save").className = "";
      document.getElementById("line").className = "";
      document.getElementById("rectangle").className = "";
      document.getElementById("circle").className = "";
      document.getElementById("ellipse").className = "";
      document.getElementById("Triangle").className = "";
      document.getElementById("square").className = "";

      //Highlight the last selected tool on toolbar
      document.getElementById(toolClicked).className = "selected";
      // Change current tool used for drawing
      this.currentTool = toolClicked;
    },
    GetMousePosition: function(x, y) {
      // Get canvas size and position in web page
      this.canvasSizeData = this.canvas.getBoundingClientRect();
      return {
        x:
          (x - this.canvasSizeData.left) *
          (this.canvas.width / this.canvasSizeData.width),
        y:
          (y - this.canvasSizeData.top) *
          (this.canvas.height / this.canvasSizeData.height),
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
          y: this.loc.y - radiusY * Math.cos(angle),
        });

        // 2 * PI equals 360 degrees
        // Divide 360 into parts based on how many triangle
        // sides you want
        angle += (2 * Math.PI) / this.TriangleSides;
      }
    },
    getTriangle() {
      this.getTrianglePoints();
      this.ctx.beginPath();
      this.ctx.moveTo(this.TrianglePoints[0].x, this.TrianglePoints[0].y);
      for (let i = 1; i < this.TriangleSides; i++) {
        this.ctx.lineTo(this.TrianglePoints[i].x, this.TrianglePoints[i].y);
      }
      this.ctx.closePath();
      this.savedTrianglePoints = this.TrianglePoints;
      this.TrianglePoints = new Array();
    },
    drawRubberbandShape: function() {
      this.ctx.strokeStyle = this.strokeColor;
      this.ctx.fillStyle = this.fillColor;
      this.ctx.lineWidth = this.line_Width;
      //   console.log(this.shapeBoundingBox.width);
      //   console.log(this.shapeBoundingBox.height);
      //   console.log(this.shapeBoundingBox.top);
      //   console.log(this.shapeBoundingBox.left);
      if (this.currentTool === "line") {
        this.ctx.beginPath();
        this.ctx.moveTo(this.mousedown.x, this.mousedown.y);
        this.ctx.lineTo(this.loc.x, this.loc.y);
        this.ctx.stroke();
      } else if (this.currentTool === "rectangle") {
        if (this.drawtype == "fill") {
          this.ctx.fillRect(
            this.shapeBoundingBox.left,
            this.shapeBoundingBox.top,
            this.shapeBoundingBox.width,
            this.shapeBoundingBox.height
          );
        } else {
          this.ctx.strokeRect(
            this.shapeBoundingBox.left,
            this.shapeBoundingBox.top,
            this.shapeBoundingBox.width,
            this.shapeBoundingBox.height
          );
        }
      } else if (this.currentTool === "square") {
        if (this.drawtype == "fill") {
          this.ctx.fillRect(
            this.shapeBoundingBox.left,
            this.shapeBoundingBox.top,
            this.shapeBoundingBox.width,
            this.shapeBoundingBox.width
          );
        } else {
          this.ctx.strokeRect(
            this.shapeBoundingBox.left,
            this.shapeBoundingBox.top,
            this.shapeBoundingBox.width,
            this.shapeBoundingBox.width
          );
        }
      } else if (this.currentTool === "circle") {
        let radius = this.shapeBoundingBox.width;
        this.ctx.beginPath();
        this.ctx.arc(
          this.mousedown.x,
          this.mousedown.y,
          radius,
          0,
          Math.PI * 2
        );
        this.strockeORfill();
      } else if (this.currentTool === "ellipse") {
        let radiusX = this.shapeBoundingBox.width;
        let radiusY = this.shapeBoundingBox.height;
        this.ctx.beginPath();
        this.ctx.ellipse(
          this.mousedown.x,
          this.mousedown.y,
          radiusX,
          radiusY,
          0,
          0,
          Math.PI * 2
        );
        this.strockeORfill();
      } else if (this.currentTool === "Triangle") {
        this.getTriangle();
        this.strockeORfill();
      }
    },
    UpdateRubberbandOnMove: function() {
      // Stores changing height, width, x & y position of most
      // top left point being either the click or mouse location
      this.UpdateRubberbandSizeData();

      // Redraw the shape
      this.drawRubberbandShape();
    },
    ReactToMouseDown(e) {
      // Change the mouse pointer to a crosshair
      this.canvas.style.cursor = "crosshair";
      // Store location
      this.loc = this.GetMousePosition(e.clientX, e.clientY);
      console.log(this.loc.x + "  " + this.loc.y);
      // Save the current canvas image
      this.SaveCanvasImage();
      // Store mouse position when clicked
      this.mousedown.x = this.loc.x;
      this.mousedown.y = this.loc.y;
      // Store that yes the mouse is being held down
      this.dragging = true;
    },
    ReactToMouseMove(e) {
      this.canvas.style.cursor = "crosshair";
      this.loc = this.GetMousePosition(e.clientX, e.clientY);

      if (this.dragging) {
        this.RedrawCanvasImage();
        this.UpdateRubberbandOnMove();
      }
    },
    ReactToMouseUp(e) {
      this.canvas.style.cursor = "default";
      this.loc = this.GetMousePosition(e.clientX, e.clientY);
      console.log(this.loc.x + "  " + this.loc.y);
      if (this.mousedown.x != this.loc.x || this.mousedown.y != this.loc.y) {
        this.RedrawCanvasImage();
        this.UpdateRubberbandOnMove();
        this.saveAndSendToServer();
      }
      this.dragging = false;
    },
    saveAndSendToServer: function() {
      this.shape["type"] = this.currentTool;
      this.shape["color"] = this.strokeColor;
      this.shape["filled"] = this.drawtype === "fill" ? true : false;
      this.shape["x"] = this.mousedown.x;
      this.shape["y"] = this.mousedown.y;
      if (this.currentTool === "line") {
        this.shape["x2"] = this.loc.x;
        this.shape["y2"] = this.loc.y;
      } else if (
        this.currentTool === "rectangle" ||
        this.currentTool === "square"
      ) {
        this.shape["x"] = this.shapeBoundingBox.left;
        this.shape["y"] = this.shapeBoundingBox.top;
        this.shape["width"] = this.shapeBoundingBox.width;
        if (this.currentTool === "rectangle")
          this.shape["length"] = this.shapeBoundingBox.length;
      } else if (this.currentTool === "circle") {
        this.shape["radius"] = this.shapeBoundingBox.width;
      } else if (this.currentTool === "ellipse") {
        this.shape["a"] = this.shapeBoundingBox.width;
        this.shape["b"] = this.shapeBoundingBox.height;
      } else if (this.currentTool === "Triangle") {
        this.shape["x"] = this.savedTrianglePoints[0].x;
        this.shape["y"] = this.savedTrianglePoints[0].y;
        for (let i = 2; i <= 3; i++) {
          let conc = "x" + i;
          this.shape[conc] = this.savedTrianglePoints[i - 1].x;
          this.shape[conc] = this.savedTrianglePoints[i - 1].y;
        }
        this.sendNewShape();
      }
    },
    SaveImage: function() {
      // Get a reference to the link element
      var imageFile = document.getElementById("img-file");
      // Set that you want to download the image when link is clicked
      imageFile.setAttribute("download", "image.png");
      // Reference the image in canvas for download
      imageFile.setAttribute("href", this.canvas.toDataURL());
    },
    strockeORfill() {
      if (this.drawtype == "fill") {
        this.ctx.fill();
      } else {
        this.ctx.stroke();
      }
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
      this.ctx.fillStyle=s['color'];
      this.ctx.strokeStyle=s['color'];
      this.ctx.lineWidth=s['lineWidth'];
      switch (s["type"]) {
        case "line":
          this.drawLine(s);
          break;
        case "rectangle":
          this.drawRectangle(s);
          break;
        case "square":
          this.drawSquare(s);
          break;
        case "circle":
          this.drawCircle(s);
          break;
        case "ellipse":
          this.drawEllipse(s);
          break;
        case "Triangle":
          this.drawTriangle(s);
          break;
      }

    },
    drawLine:function(s){
        this.ctx.beginPath();
        this.ctx.moveTo(s['x'], s['y']);
        this.ctx.lineTo(s['x2'], s['y2']);
        this.ctx.stroke();
        this.ctx.closePath();
    },
    drawRectangle:function(s){
        if (s['filled']) {
          this.ctx.fillRect(
            s['x'],
            s['y'],
            s['width'],
            s['length']
          );
        } else {
          this.ctx.strokeRect(
            s['x'],
            s['y'],
            s['width'],
            s['length']
          );
        }
    },
    drawSquare:function(s){
        if (s['filled']) {
          this.ctx.fillRect(
           s['x'],
            s['y'],
            s['width'],
            s['width']
          );
        } else {
          this.ctx.strokeRect(
            s['x'],
            s['y'],
            s['width'],
            s['width']
          );
        }
    },
    drawCircle:function(s){
        this.ctx.beginPath();
        this.ctx.arc(
          s['x'],
          s['y'],
          s['radius'],
          0,
          Math.PI * 2
        );
        s['filled']?this.ctx.fill():this.ctx.stroke();
        this.ctx.closePath();
    },
    drawEllipse:function(s){
        this.ctx.beginPath();
        this.ctx.ellipse(
          s['x'],
          s['y'],
          s['a'],
          s['b'],
          0,
          0,
          Math.PI * 2
        );
        s['filled']?this.ctx.fill():this.ctx.stroke();
        this.closePath();
    },
    drawTriangle:function(s){
      this.ctx.beginPath();
      this.ctx.moveTo(s['x'],s['y']);
      this.ctx.lineTo(s['x2'],s['y2']);
      this.ctx.lineTo(s['x3'],s['y3']);
      this.ctx.closePath();
      s['filled']?this.ctx.fill():this.ctx.stroke();
    },
    sendNewShape: function() {
      axios.post("http://localhost:8081" + "/add", this.shape);
    },
    getShapesFromServer: function() {
      let shapes = axios.get("http://localhost:8081" + "/shapes");
      console.log(shapes);
      this.ctx.save();
      for (let s in shapes) {
        this.drawShape(s);
      }
      this.ctx.restore();
    },
  },
};
</script>
//Not much but honest work
