<template>
  <div class="add-shape-menu">

    <ul class="shape-selection" v-if="layer !== -1" :class="{'modal-list': modal}">
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.Line')">
        Line
      </li>
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.Circle')">
        Circle
      </li>
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.Polygon')">
        Polygon
      </li>
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.RegularPolygon')">
        Regular Polygon
      </li>
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.Ellipse')">
        Ellipse
      </li>
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.Star')">
        Star
      </li>
      <li class="clickable" v-on:click="addShape(shapesRootPackage + '.Text')">
        Text
      </li>
    </ul>

    <hr />

    <ul v-if="modal" class="modal-list">
      <li class="cancel clickable" v-on:click="close()">
        Cancel
      </li>
    </ul>

  </div>
</template>

<script>
  import {dataBus} from "../../main";

  /**
   * This component renders the add shape menu.
   * Upon confirmation, it sends an "addShape" event over the {@link dataBus}.
   */
  export default {

    name: "AddShapeMenu",

    props: {
      modal: Boolean,
      layer: Number
    },

    data() {
      return {
        shapesRootPackage: "shapes"
      }
    },

    methods: {
      addShape: function(shapeClass) {
        dataBus.$emit('addShape', {
          layerIndex: this.layer,
          shapeClass: shapeClass
        });
        if (this.modal) {
          this.close();
        }
      },
      close: function() {
        this.$emit('close');
      }
    }

  }

</script>

<style scoped>

  .shape-selection {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
  }

</style>
