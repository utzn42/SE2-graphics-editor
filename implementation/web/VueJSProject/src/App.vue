<template>
  <div id="app">
    <Connection base-url="http://localhost:8080"/>
    <div class="app-grid">
      <div id="top-bar-menu" class="bar-menu">
        <TopBarMenu />
      </div>
      <div id="left-menu">
        <LeftMenu />
      </div>
      <div id="canvas">
        <Canvas />
      </div>
      <div id="right-menu">
        <RightMenu />
      </div>
      <div id="bottom-bar-menu" class="bar-menu">
        <BottomBarMenu />
      </div>
    </div>

  </div>
</template>

<script>

  import Canvas from "./components/Canvas";
  import Connection from "./components/Connection";
  import {dataBus} from "./main";
  import BottomBarMenu from "./components/BottomBarMenu";
  import TopBarMenu from "./components/TopBarMenu";
  import LeftMenu from "./components/LeftMenu";
  import RightMenu from "./components/RightMenu";

  /**
   * The main component of the client.
   * Contains {@link Canvas}, {@link Connection}, {@link BottomBarMenu}, {@link ShapeMenu} and {@link LayerMenu}.
   * Upon mount, it sends a "create" event over the {@link dataBus}.
   */
  export default {

    name: 'App',

    components: {
      RightMenu,
      LeftMenu,
      TopBarMenu,
      BottomBarMenu,
      Canvas,
      Connection
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
    color: #2c3e2c;
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
    grid-template-rows: 30px auto 30px;
    box-sizing: border-box;
    background-color: darkgray;
    height: 100%;
    width: 100%;
  }

  .app-grid div {
    box-sizing: border-box;
    overflow: auto;
  }

  #left-menu {
    grid-column: 1;
    grid-row: 2;
  }

  #canvas {
    grid-column: 2;
    grid-row: 2;
    background-image: url("./assets/bgpattern50.png");
  }

  #right-menu {
    grid-column: 3;
    grid-row: 2;
  }

  #top-bar-menu {
    grid-row: 1;
    grid-column-start: 1;
    grid-column-end: 4;
    background-color: #777777;
  }

  #bottom-bar-menu {
    grid-row: 3;
    grid-column-start: 1;
    grid-column-end: 4;
    background-color: #777777;
  }

  ul {
    list-style: none;
    box-sizing: border-box;
    padding: 0;
  }

  li {
    padding: 10px 5px;
  }

  .clickable:hover {
    cursor: pointer;
  }

  li:hover {
    background-color: #ffffff;
  }

  li.selected, li.selected-dark {
    color: white;
  }

  li.selected {
    background-color: #227722;
  }

  li.selected:hover {
    background-color: #115511;
  }

  li.selected-dark {
    background-color: #666666;
  }

  li.selected-dark:hover {
    background-color: #555555;
  }

  li.selected ul, li.selected-dark ul {
    color: #2c3e2c;
    background-color: darkgray;
  }

  .modal-list {
    background-color: #ffffff;
  }

  .modal-list li:hover {
    color: white;
    background-color: #227722;
  }

  .modal-list .cancel:hover {
    background-color: darkred;
  }

  li.confirm, button {
    color: #ffffff;
    cursor: pointer;
    background: #227722;
  }

  button {
    padding: 5px 10px;
    border: none;
    margin: 0;
  }

  li.confirm:hover, button:hover {
    background: #115511;
  }

  ul.horizontal-list {
    list-style-type: none;
    padding: 0;
    margin: 0;
  }

  ul.horizontal-list li {
    float: left;
  }

  .white-border {
    border: solid 2px white;
  }

  .shape-edit-form {
    padding: 10px;
  }

</style>
