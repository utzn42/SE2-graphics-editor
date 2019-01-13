<template>

  <div class="layer-menu-grid">
    <ul>
      <template v-for="element in elements">
        <template v-if="element.hasOwnProperty('shape')">
          <layer v-bind:id="element.id" v-bind:visible="element.visible" v-bind:shape="element.shape"></layer>
        </template>
        <template v-else>
          <layer-group v-bind:id="element.id" v-bind:visible="element.visible" v-bind:elements="element.elements"></layer-group>
        </template>
      </template>
      <hr v-if="elements.length !== 0"/>
      <li class="clickable" v-on:click.stop="showAddShape=true">
        <i>Add Element</i>
      </li>

      <ModalComponent v-show="showAddShape">
        <AddShapeMenu modal v-on:close="showAddShape=false" />
      </ModalComponent>

    </ul>
  </div>

</template>

<script>
  import {dataBus} from "../../main";
  import Layer from "../shapedata/Layer";
  import LayerGroup from "../shapedata/LayerGroup";
  import ModalComponent from "./ModalComponent";
  import AddShapeMenu from "./AddShapeMenu";

  export default {
    name: "LayerMenuNew",
    components: {AddShapeMenu, ModalComponent, LayerGroup, Layer},
    data() {
      return {
        elements: [],
        showAddShape: false
      }
    },

    methods: {
      updateElements: function(canvas) {
        this.elements = canvas.elements;
        console.log(this.elements);
      }
    },

    created: function() {
      dataBus.$on('response', response => this.updateElements(response.body.canvas));
    }
  }
</script>

<style scoped>

  .layer-menu-grid {
    height: 100%;
    width: 100%;
  }

</style>
