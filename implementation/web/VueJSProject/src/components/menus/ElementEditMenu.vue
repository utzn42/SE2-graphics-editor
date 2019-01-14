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

      <div v-if="selectedElement.hasOwnProperty('shape') && selectedElement.shape.hasOwnProperty('transformations') && selectedElement.shape.transformations.length > 0">
        Applied Transformations:
        <br />
        <p v-for="transformation in selectedElement.shape.transformations">
          <i>{{transformation.type}}</i>
        </p>
        <br />
        <li class="clickable" v-on:click="clearTransformations()">
          <i>Clear all transformations</i>
        </li>
      </div>
      <button v-on:click="showTransformMenu=true">
        Add transformation
      </button>

      <br />
      <hr />

      <li class="clickable" v-on:click="removeElement()">
        <i>Remove Element</i>
      </li>


    </ul>

    <ModalComponent v-show="showTransformMenu">
      <TransformMenu modal :element-id="selectedElement.id" v-on:close="showTransformMenu=false" />
    </ModalComponent>

  </div>
</template>

<script>
  import {dataBus} from "../../main";
  import ModalComponent from "./ModalComponent";
  import TransformMenu from "./TransformMenu";

  /**
   * This component renders the element edit menu.
   * Upon confirmation, it sends an "editElement" or "removeElement" event over the {@link dataBus}.
   */
  export default {

    name: "ElementEditMenu",
    components: {TransformMenu, ModalComponent},
    data() {
      return {
        selectedElement: null,
        visible: true,
        showTransformMenu: false
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
      },

      clearTransformations: function() {
        dataBus.$emit('transformElement', {
          elementID: this.selectedElement.id,
          clearAll: true
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
