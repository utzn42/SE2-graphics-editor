<template>
  <div class="canvas">
    <div class="svg" v-html="canvas.html"></div>
  </div>
</template>

<script>
  import {dataBus} from "../main";

  export default {
    name: "Canvas",
    data() {
      return {
        canvas: {}
      }
    },
    methods: {
      updateCanvas: function(canvas) {
        this.canvas = canvas;
      }
    },
    created: function() {
      dataBus.$on('response', (response) => this.updateCanvas(response.body.canvas));
    }
  }
</script>

<style scoped>

  .canvas {
    height: 100%;
    width: 100%;
    background-image: url("../assets/bgpattern50.png");
    display: grid;
    overflow: auto;
  }

  .svg {
    align-self: center;
    justify-self: center;
  }

  .svg >>> svg {
    border: 2px dashed #666666;
  }

</style>
