<template>
  <div v-if="selectedElement !== null && selectedElement !== undefined" class="shape-edit-form">

    <ul>
      <li class="selected-dark">
        Element ID: {{selectedElement.id}}
      </li>
      <hr />
      <li class="clickable" v-on:click="toggleVisibility()">
        Visible
        <input type="checkbox" :checked="visible" />
      </li>
      <hr />
      <li class="clickable" v-on:click="removeElement()">
        <i>Remove Element</i>
      </li>
    </ul>

  </div>
</template>

<script>
  import {dataBus} from "../../main";

  /**
   * This component renders the element edit menu.
   * Upon confirmation, it sends an "editElement" or "removeElement" event over the {@link dataBus}.
   */
  export default {

    name: "ElementEditMenu",

    data() {
      return {
        selectedElement: null,
        visible: true
      }
    },

    methods: {

      updateElementEdit: function(element) {
        this.selectedElement = element;
        this.visible = this.selectedElement.visible;
      },

      toggleVisibility: function() {
        this.visible = !this.visible;
        dataBus.$emit('editElement', {
          elementID: this.selectedElement.id,
          visible: this.visible
        });
      },

      removeElement: function() {
        dataBus.$emit('removeElement', {
          elementID: this.selectedElement.id
        });
        dataBus.$emit('pushElement', null);
      }

    },

    created: function () {
      dataBus.$on('pushElement', (element) => this.updateElementEdit(element));
    }

  }
</script>

<style scoped>

  table {
    display: inline-table;
  }

</style>
