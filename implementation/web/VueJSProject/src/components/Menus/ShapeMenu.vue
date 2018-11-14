<template>
  <div class="shape-menu-grid">
    <div class="shape-menu-edit">
      <ShapeEditForm />
    </div>
    <div class="shape-menu-shapes">
      <ul v-if="selectedLayer !== -1">
        <li v-on:click="addShape(shapesRootPackage + '.Line')">
          + Add Line
        </li>
        <li v-on:click="addShape(shapesRootPackage + '.Circle')">
          + Add Circle
        </li>
        <li v-on:click="addShape(shapesRootPackage + '.Polygon')">
          + Add Polygon
        </li>
        <li v-on:click="addShape(shapesRootPackage + '.RegularPolygon')">
          + Add Regular Polygon
        </li>
        <li v-on:click="addShape(shapesRootPackage + '.Ellipse')">
          + Add Ellipse
        </li>
        <li v-on:click="addShape(shapesRootPackage + '.Star')">
          + Add Star
        </li>
        <li v-on:click="addShape(shapesRootPackage + '.Text')">
          + Add Text
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import {dataBus} from "../../main";
  import {Circle, Ellipse, Line, Polygon, RegularPolygon, Star, Text} from "../ShapeData/ShapeData";
  import ShapeEditForm from "./ShapeEditForm";

  export default {
    name: "ShapeMenu",
    components: {
      ShapeEditForm
    },
    data() {
      return {
        canvasData: {
          width: {
            label: "Canvas Width",
            type: "simple",
            value: ""
          },
          height: {
            label: "Canvas Height",
            type: "simple",
            value: ""
          }
        },
        shapeClass: null,
        shapeData: null,
        shapeModel: {},
        selectedLayer: null,
        selectedShape: null,
        shapesRootPackage: "shapes"
      }
    },
    methods: {
      updateEdit: function(layerData) {
        this.selectedLayer = layerData.selectedLayer;
        this.selectedShape = layerData.selectedShape;
        if (this.selectedLayer === -1 || this.selectedShape === -1) {
          this.shapeClass = null;
          this.shapeModel = {
            width: this.canvasData.width.value,
            height: this.canvasData.height.value
          };
          this.shapeData = this.canvasData;
        }
        else {
          let shape = layerData.layers[this.selectedLayer].shapes[this.selectedShape];
          this.shapeModel = shape;
          this.shapeClass = shape.shapeClass;
          switch (this.shapeClass) {
            case this.shapesRootPackage + ".Circle":
              this.shapeData = new Circle(shape.center, shape.radius, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case this.shapesRootPackage + ".RegularPolygon":
              this.shapeData = new RegularPolygon(shape.edgeAmount, shape.center, shape.radius, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case this.shapesRootPackage + ".Star":
              this.shapeData = new Star(shape.innerRadius, shape.edgeAmount, shape.center, shape.radius, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case this.shapesRootPackage + ".Ellipse":
              this.shapeData = new Ellipse(shape.center, shape.radiusX, shape.radiusY, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case this.shapesRootPackage + ".Line":
              this.shapeData = new Line(shape.coordinates, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case this.shapesRootPackage + ".Polygon":
              this.shapeData = new Polygon(shape.coordinates, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case this.shapesRootPackage + ".Text":
              this.shapeData = new Text(shape.center, shape.displayText, shape.font, shape.fontSize, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            default:
              console.log("Unexpected Type");
          }
        }
        dataBus.$emit('setShapeEditForm', {
          shapeData: this.shapeData,
          shapeModel: this.shapeModel,
          selectedLayer: this.selectedLayer,
          selectedShape: this.selectedShape
        });
      },
      updateCanvasVars: function(canvas) {
        this.canvasData.width = {
          label: "Canvas Width",
          type: "simple",
          value: canvas.width
        };
        this.canvasData.height = {
          label: "Canvas Height",
          type: "simple",
          value: canvas.height
        };
      },
      addShape: function(shapeClass) {
        dataBus.$emit('addShape', {
          layerIndex: this.selectedLayer,
          shapeClass: shapeClass
        });
      }
    },
    created: function () {
      dataBus.$on('layersUpdated', (layerData) => this.updateEdit(layerData));
      dataBus.$on('canvasUpdated', (canvas) => this.updateCanvasVars(canvas));
    }
  }
</script>

<style scoped>

  ul {
    list-style: none;
    box-sizing: border-box;
    padding: 0;
  }

  li {
    padding: 10px 5px;
  }

  li:hover {
    cursor: pointer;
    background-color: #ffffff;
  }

  .shape-menu-grid {
    display: grid;
    grid-template-columns: auto;
    grid-template-rows: 70% 30%;
    height: 100%;
    width: 100%;
    background-color: darkgray;
  }

  .shape-menu-edit {
    grid-column: 1;
    grid-row: 1;
    overflow-y: auto;
  }

  .shape-menu-shapes {
    grid-column: 1;
    grid-row: 2;
    overflow-y: auto;
  }

</style>
