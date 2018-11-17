<template>
  <div v-if="shapeData !== null && shapeData !== undefined" class="shape-edit-form">

    <ul>
      <template v-for="(attribute, key) in shapeData">

        <li>

          <label class="header-label">{{attribute.label}}</label><br />

          <template v-if="attribute.type === 'simple'">
            <input type="text" v-model="shapeModel[key]" v-on:keyup.enter="edit()">
          </template>

          <template v-if="attribute.type === 'range:0-1'">
            <input type="range" min="0" max="1" step="any" v-model="shapeModel[key]" v-on:change="edit()">
          </template>

          <template v-if="attribute.type === 'colour'">
            <input type="color" v-model="shapeModel[key].rgbColour" v-on:change="edit()"><br />
            <label>Opacity </label>
            <input type="range" min="0" max="1" step="any" v-model="shapeModel[key].opacity" v-on:change="edit()">
          </template>

          <template v-if="attribute.type ==='coordinate'">
            <label>x: </label>
            <input type="text" v-model="shapeModel[key].x" v-on:keyup.enter="edit()"><br />
            <label>y: </label>
            <input type="text" v-model="shapeModel[key].y" v-on:keyup.enter="edit()">
          </template>

          <template v-if="attribute.type ==='coordinateArray'">
            <template v-for="(coordinate, index) in attribute.value">
              <table>
                <tr>
                  <td>
                    <label>x: </label>
                    <input type="text" v-model="shapeModel[key][index].x" v-on:keyup.enter="edit()">
                  </td>
                </tr>
                <tr>
                  <td>
                    <label>y: </label>
                    <input type="text" v-model="shapeModel[key][index].y" v-on:keyup.enter="edit()">
                  </td>
                </tr>
              </table>
              <button v-on:click="removeVertex(shapeModel[key], index)">Remove</button>
              <br />
            </template>
            <button v-on:click="addVertex(shapeModel[key])">Add Point</button>
          </template>

        </li>

        <hr />

      </template>

    </ul>

    <br />
    <button v-on:click="edit()">Apply Changes</button>

  </div>
</template>

<script>
  import {dataBus} from "../../main";
  import {Circle, Ellipse, Line, Polygon, RegularPolygon, Star, Text} from "../shapedata/shapedata";

  /**
   * This component renders the shape edit menu.
   * Upon confirmation, it sends an "editShape" event over the {@link dataBus}.
   */
  export default {

    name: "ShapeEditMenu",

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

      addVertex: function (object) {
        object.push({
          x: 0,
          y: 0
        });
        this.edit();
      },

      removeVertex: function (object, index) {
        object.splice(index, 1);
        this.edit();
      },

      edit: function () {
        if (this.selectedLayer !== -1 && this.selectedShape !== -1) {
          dataBus.$emit('editShape', {
            layerIndex: this.selectedLayer,
            shapeIndex: this.selectedShape,
            shape: this.shapeModel
          });
        } else {
          dataBus.$emit('editCanvas', {
            width: this.shapeModel.width,
            height: this.shapeModel.height
          });
        }
      }
    },

    created: function () {
      dataBus.$on('layersUpdated', (layerData) => this.updateEdit(layerData));
      dataBus.$on('canvasUpdated', (canvas) => this.updateCanvasVars(canvas));
    }

  }
</script>

<style scoped>

  table {
    display: inline-table;
  }

</style>
