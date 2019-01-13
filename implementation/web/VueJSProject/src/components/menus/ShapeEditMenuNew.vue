<template>
  <div v-if="shapeData !== null && shapeData !== undefined" class="shape-edit-form">

    <ul>

      <li class="selected-dark">Shape Attributes</li>

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

      <br />
      <li class="confirm" v-on:click="edit()">
        Apply Changes
      </li>

    </ul>

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

    name: "ShapeEditMenuNew",

    data() {
      return {
        shapeType: "",
        shapeData: null,
        shapeModel: {},
        selectedElement: {}
      }
    },

    methods: {

      updateEdit: function(element) {
        this.selectedElement = element;
        if (this.selectedElement.hasOwnProperty("shape")) {
          let shape = this.selectedElement.shape;
          this.shapeModel = shape;
          this.shapeType = shape.shapeType;
          switch (this.shapeType) {
            case "CIRCLE":
              this.shapeData = new Circle(shape.center, shape.radius, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case "REGULAR_POLYGON":
              this.shapeData = new RegularPolygon(shape.edgeAmount, shape.center, shape.radius, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case "STAR":
              this.shapeData = new Star(shape.innerRadius, shape.edgeAmount, shape.center, shape.radius, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case "ELLIPSE":
              this.shapeData = new Ellipse(shape.center, shape.radiusX, shape.radiusY, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case "LINE":
              this.shapeData = new Line(shape.coordinates, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case "POLYGON":
              this.shapeData = new Polygon(shape.coordinates, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            case "TEXT":
              this.shapeData = new Text(shape.center, shape.displayText, shape.font, shape.fontSize, shape.fillColour, shape.strokeColour, shape.strokeWidth, shape.opacity);
              break;
            default:
              console.log("Unexpected Type");
          }
        } else {
          this.shapeData = null;
        }
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
        this.shapeModel.elementID = this.selectedElement.id;
        dataBus.$emit('modifyShape', this.shapeModel);
        console.log(this.shapeModel);
      }
    },

    created: function () {
      dataBus.$on('pushElement', (element) => this.updateEdit(element));
    }

  }
</script>

<style scoped>

  table {
    display: inline-table;
  }

</style>
