<template>
  <div id="app">
    <Connection base-url="http://localhost:8080"/>
    <div class="app-grid">
      <div id="layer-menu">
        <LayerMenu />
      </div>
      <div id="canvas">
        <Canvas />
      </div>
      <div id="shape-menu">
        <ShapeMenu />
      </div>
      <div id="bar-menu">
        <BarMenu />
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
  import BarMenu from "./components/Menus/BarMenu";

  export default {
    name: 'App',
    components: {
      BarMenu,
      ShapeMenu,
      Canvas,
      Connection,
      LayerMenu
    },
    mounted: function () {
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
    grid-template-rows: auto 30px;
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

  #bar-menu {
    grid-column-start: 1;
    grid-column-end: 4;
    grid-row: 2;
  }

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

  li.selected {
    color: white;
    background-color: #227722;
  }

  li.selected:hover {
    background-color: #115511;
  }

  button {
    padding: 5px 10px;
    border: none;
    color: #ffffff;
    cursor: pointer;
    background: #227722;
    margin: 0;
  }

  button:hover {
    background: #115511;
  }

</style>
