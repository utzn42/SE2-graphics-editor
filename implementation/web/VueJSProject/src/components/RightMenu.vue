<template>

  <div class="right-menu">
    <ul class="horizontal-list edit-menu-tabs">
      <li class="clickable" v-if="showCanvasEditMenu" v-on:click="currentMenu='canvas'" :class="{'selected-dark': currentMenu === 'canvas'}">
        Project Settings
      </li>
      <li class="clickable" v-if="showElementEditMenu" v-on:click="currentMenu='element'" :class="{'selected-dark': currentMenu === 'element'}">
        Element Settings
      </li>
      <li class="clickable" v-if="showShapeEditMenu" v-on:click="currentMenu='shape'" :class="{'selected-dark': currentMenu === 'shape'}">
        Shape Attributes
      </li>
    </ul>

    <div class="edit-menu">
      <CanvasEditMenu :class="{hidden: currentMenu !== 'canvas'}" />
      <ElementEditMenu :class="{hidden: currentMenu !== 'element'}" />
      <ShapeEditMenuNew :class="{hidden: currentMenu !== 'shape'}" />
    </div>

  </div>

</template>

<script>
  import ShapeEditMenuNew from "./menus/ShapeEditMenuNew";
  import ElementEditMenu from "./menus/ElementEditMenu";
  import CanvasEditMenu from "./menus/CanvasEditMenu";
  import {dataBus} from "../main";

  /**
   * This component renders the right menu.
   */
  export default {

    name: "RightMenu",

    data() {
      return {
        showCanvasEditMenu: true,
        showElementEditMenu: false,
        showShapeEditMenu: false,
        currentMenu: 'canvas'
      }
    },

    components: {
      CanvasEditMenu,
      ElementEditMenu,
      ShapeEditMenuNew
    },

    methods: {

      showOptionsForElement: function(element) {
        this.showCanvasEditMenu = true;
        if (element === null) {
          this.showElementEditMenu = false;
          this.showShapeEditMenu = false;
          this.currentMenu = 'canvas';
        } else if (element.hasOwnProperty("shape")) {
          this.showElementEditMenu = true;
          this.showShapeEditMenu = true;
        } else {
          this.showElementEditMenu = true;
          this.showShapeEditMenu = false;
        }
      }

    },

    created() {
      dataBus.$on('pushElement', (element) => this.showOptionsForElement(element));
    }

  }

</script>

<style scoped>

  .right-menu {
    display: grid;
    grid-template-rows: auto;
    grid-template-columns: 1fr;
  }

  .edit-menu-tabs {
    grid-row: 1;
  }

  .edit-menu {
    grid-row: 2;
  }

  .hidden {
    display: none;
  }

</style>
