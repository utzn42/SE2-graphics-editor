<template>
  <div v-if="shapeData !== null" class="shape-edit-form">
    <template v-for="(attribute, key) in shapeData">
      <label class="header-label">{{attribute.label}}</label><br />
      <template v-if="attribute.type === 'simple'">
        <input type="text" v-model="shapeModel[key]" v-on:keyup.enter="edit()">
      </template>
      <template v-if="attribute.type === 'colour'">
        <input type="text" v-model="shapeModel[key]" v-on:keyup.enter="edit()">
      </template>
      <template v-if="attribute.type ==='coordinate'">
        <label>x: </label>
        <input type="text" v-model="shapeModel[key].x" v-on:keyup.enter="edit()"><br />
        <label>y: </label>
        <input type="text" v-model="shapeModel[key].y" v-on:keyup.enter="edit()">
      </template>
      <template v-if="attribute.type ==='coordinateArray'">
        <template v-for="(coordinate, index) in attribute.value">
          <label>{{index + 1}} </label><button v-on:click="removeVertex(shapeModel[key], index)">Remove</button><br />
          <label>x: </label>
          <input type="text" v-model="shapeModel[key][index].x" v-on:keyup.enter="edit()"><br />
          <label>y: </label>
          <input type="text" v-model="shapeModel[key][index].y" v-on:keyup.enter="edit()"><br />
        </template>
        <button v-on:click="addVertex(shapeModel[key])">Add Point</button>
      </template>
      <hr />
    </template>
  </div>
</template>

<script>
  import {dataBus} from "../../main";

  export default {
    name: "ShapeEditForm",
    data() {
      return {
        shapeData: null,
        shapeModel: null,
        selectedLayer: null,
        selectedShape: null
      }
    },
    methods: {
      addVertex: function(object) {
        object.push({
          x: 0,
          y: 0
        });
        this.edit();
      },
      removeVertex: function(object, index) {
        object.splice(index, 1);
        this.edit();
      },
      edit: function() {
        dataBus.$emit('editShape', {
          layerIndex: this.selectedLayer,
          shapeIndex: this.selectedShape,
          shape: this.shapeModel
        })
      }
    },
    created: function() {
      dataBus.$on('setShapeEditForm', (data) => {
        this.shapeData = data.shapeData;
        this.shapeModel = data.shapeModel;
        this.selectedLayer = data.selectedLayer;
        this.selectedShape = data.selectedShape;
      });
    }
  }
</script>

<style scoped>

</style>
