<template>

  <li class="clickable" v-on:click.stop="pushThis()" :class="{selected: isSelected}">
    {{shape.shapeType}}
  </li>

</template>

<script>
  import {dataBus} from "../../main";
  import {Layer} from "./canvaselement";

  export default {
    name: "Layer",

    data() {
      return {
        isSelected: false
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
        dataBus.$emit("pushElement", (new Layer(this.id, this.visible, this.shape)));
      },

      unselectIfNotThis: function(element) {
        if (element === null || element === undefined || !element.hasOwnProperty("id") || element.id !== this.id) {
          this.isSelected = false;
        }
      }

    },

    props: {
      id: Number,
      visible: Boolean,
      shape: {}
    },

    created() {
      dataBus.$on("requestElement", (requestId) => this.checkIDAndSend(requestId));
      dataBus.$on("pushElement", (element) => this.unselectIfNotThis(element));
    }
  }
</script>

<style scoped>

</style>
