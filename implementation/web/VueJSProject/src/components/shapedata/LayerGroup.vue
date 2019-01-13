<template>

  <li class="clickable" v-on:click.stop="pushThis()" :class="{'selected': isSelected, 'selected-dark': !isSelected}">
    Layer Group
    <ul class="white-border" style="margin-top:20px; margin-left:5px;">
      <template v-for="element in elements">
        <template v-if="element.hasOwnProperty('shape')">
          <layer v-bind:id="element.id" v-bind:visible="element.visible" v-bind:shape="element.shape"></layer>
        </template>
        <template v-else>
          <layer-group v-bind:id="element.id" v-bind:visible="element.visible" v-bind:elements="element.elements"></layer-group>
        </template>
      </template>
      <hr v-if="elements.length !== 0"/>
      <li v-on:click.stop="showAddShape=true">
        <i>Add Element</i>
      </li>
    </ul>

    <ModalComponent v-show="showAddShape">
      <AddShapeMenu modal :element-id="id" add-into v-on:close="showAddShape=false" />
    </ModalComponent>

  </li>

</template>

<script>
  import Layer from "./Layer";
  import {LayerGroup} from "./canvaselement";
  import {dataBus} from "../../main";
  import ModalComponent from "../menus/ModalComponent";
  import AddShapeMenu from "../menus/AddShapeMenu";

  export default {
    name: "LayerGroup",
    components: {AddShapeMenu, ModalComponent, Layer},

    data() {
      return {
        isSelected: false,
        showAddShape: false
      }
    },

    methods: {

      checkIDAndSend: function(requestId) {
        if (requestId === this.id) {
          this.pushThis();
        }
      },

      pushThis: function() {
        this.isSelected = true;
        dataBus.$emit("pushElement", (new LayerGroup(this.id, this.visible, this.elements)));
      },

      unselectIfNotThis: function(element) {
        if (element.id !== this.id) {
          this.isSelected = false;
        }
      }

    },

    props: {
      id: Number,
      visible: Boolean,
      elements: Array
    },

    created() {
      dataBus.$on("requestElement", (requestId) => this.checkIDAndSend(requestId));
      dataBus.$on("pushElement", (element) => this.unselectIfNotThis(element));
    }
  }
</script>

<style scoped>

</style>
