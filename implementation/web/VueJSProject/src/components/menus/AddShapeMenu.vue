<template>
  <div class="add-shape-menu">

    <ul class="shape-selection" :class="{'modal-list': modal}">
      <li class="clickable" v-on:click="addShape('LINE')">
        Line
      </li>
      <li class="clickable" v-on:click="addShape('CIRCLE')">
        Circle
      </li>
      <li class="clickable" v-on:click="addShape('POLYGON')">
        Polygon
      </li>
      <li class="clickable" v-on:click="addShape('REGULAR_POLYGON')">
        Regular Polygon
      </li>
      <li class="clickable" v-on:click="addShape('ELLIPSE')">
        Ellipse
      </li>
      <li class="clickable" v-on:click="addShape('STAR')">
        Star
      </li>
      <li class="clickable" v-on:click="addShape('TEXT')">
        Text
      </li>
    </ul>

    <hr />

    <ul :class="{'modal-list': modal}">
      <li class="clickable" v-on:click="addLayerGroup()">
        Layer Group
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
      elementId: Number,
      addBefore: Boolean,
      addInto: Boolean
    },

    data() {
      return {
        shapesRootPackage: "shapes"
      }
    },

    methods: {
      addShape: function(shapeType) {
        if (this.addInto) {
          dataBus.$emit('addShape', {
            shapeType: shapeType,
            placeIntoElementID: this.elementId
          });
        } else if (this.addBefore) {
          dataBus.$emit('addShape', {
            shapeType: shapeType,
            placeBeforeElementID: this.elementID
          });
        } else {
          dataBus.$emit('addShape', {
            shapeType: shapeType
          })
        }

        if (this.modal) {
          this.close();
        }
      },

      addLayerGroup: function() {
        if (this.addInto) {
          dataBus.$emit('addLayerGroup', {
            placeIntoElementID: this.elementId
          });
        } else if (this.addBefore) {
          dataBus.$emit('addLayerGroup', {
            placeBeforeElementID: this.elementId
          });
        } else {
          dataBus.$emit('addLayerGroup', {});
        }

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
