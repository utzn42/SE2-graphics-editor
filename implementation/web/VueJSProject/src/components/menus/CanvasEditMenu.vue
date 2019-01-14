<template>
  <div v-if="canvas !== null && canvas !== undefined" class="shape-edit-form">

    <ul>

      <li class="selected-dark">
        Project Settings
      </li>

      <hr />

      <li>
        Canvas Width
        <input type="text" v-model="canvas.width" v-on:keyup.enter="editCanvas()">
      </li>
      <li>
        Canvas Height
        <input type="text" v-model="canvas.height" v-on:keyup.enter="editCanvas()">
      </li>

      <hr />

      <li class="clickable" v-on:click="toggleAllowTransform()">
        Allow Shape Transformations
        <input type="checkbox" :checked="canvas.allowTransformAttribute" />
      </li>

      <hr />

      <br />
      <li class="confirm" v-on:click="editCanvas()">
        Apply Changes
      </li>

    </ul>

  </div>
</template>

<script>
  import {dataBus} from "../../main";

  /**
   * This component renders the canvas edit menu.
   * Upon confirmation, it sends an "editProject" event over the {@link dataBus}.
   */
  export default {

    name: "CanvasEditMenu",

    data() {
      return {
        canvas: null
      }
    },

    methods: {

      updateCanvasEdit: function(canvas) {
        this.canvas = canvas;
      },

      editCanvas: function() {
        dataBus.$emit('editProject', {
          width: this.canvas.width,
          height: this.canvas.height,
          allowTransformAttribute: this.canvas.allowTransformAttribute
        });
      },

      toggleAllowTransform: function() {
        this.canvas.allowTransformAttribute = !this.canvas.allowTransformAttribute;
        this.editCanvas();
        dataBus.$emit('pushElement', null);
      }

    },

    created: function () {
      dataBus.$on('response', (response) => this.updateCanvasEdit(response.body.canvas));
    }

  }
</script>

<style scoped>

  table {
    display: inline-table;
  }

</style>
