<template>
  <div id="app">
    <Connection base-url="http://localhost:8080" />
    <div class="app-grid">
      <div id="layer-menu">
        <LayerMenu/>
      </div>
      <div id="canvas">
        <Canvas/>
      </div>
      <div id="shape-menu">
        <ShapeMenu />
      </div>
    </div>

  </div>
</template>

<script>
  import Canvas from "./components/Canvas";
  import Connection from "./components/Connection";
  import LayerMenu from "./components/Menus/LayerMenu";
  import ShapeMenu from "./components/Menus/ShapeMenu";
  import {dataBus} from "./main";

  export default {
    name: 'App',
    components: {
      ShapeMenu,
      Canvas,
      Connection,
      LayerMenu
    },
    mounted: function() {
      dataBus.$emit("create");
    }
  }
</script>

<style>

  * {
    margin: 0;
    padding: 0;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    text-align: center;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    background-color: black;
  }

  .app-grid {
    display: grid;
    grid-template-columns: 20% 60% 20%;
    grid-template-rows: auto;
    box-sizing: border-box;
    background-color: darkgray;
    height: 100%;
    width: 100%;
  }

  .app-grid div {
    box-sizing: border-box;
    overflow: auto;
  }

  #layer-menu {
    grid-column: 1;
    grid-row: 1;
  }

  #canvas {
    grid-column: 2;
    grid-row: 1;
  }

  #shape-menu {
    grid-column: 3;
    grid-row: 1;
  }
</style>
