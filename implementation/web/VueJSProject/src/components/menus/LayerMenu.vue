<template>
  <div class="layer-menu-grid">

    <ul>

      <li class="clickable" v-on:click="selectLayer(-1)" :class="{'selected-dark': selectedLayer === -1}">
        <i>{{layerName(-1)}}</i>
      </li>

      <li class="clickable" v-for="(layer, index) in layers" :class="{'selected-dark': index === selectedLayer}" v-on:click="selectLayer(index)">

        {{layerName(index)}}
        <input type="checkbox" :checked="layer.visible" v-on:click.stop="toggleVisibility(index)">

        <div class="white-border" v-if="index === selectedLayer" style="margin-top:20px;">
          <ul>
            <li class="clickable" v-for="(shape, shapeIndex) in layer.shapes" :class="{selected: shapeIndex === selectedShape}" v-on:click.stop="selectShape(shapeIndex)">
              Shape {{shapeIndex}} ({{shape.shapeClass.split(".").reverse()[0]}})
            </li>
          </ul>
          <hr v-if="layer.shapes.length > 0" />
          <ul>
            <li class="clickable" v-on:click.stop="showAddShape=true">
              <b>+ Add Shape</b>
            </li>
            <li class="clickable" v-if="selectedShape !== -1" v-on:click="deleteShape()">
              <b>&ndash; Delete Selected Shape</b>
            </li>
          </ul>
        </div>

      </li>
    </ul>

    <hr v-if="layers.length > 0" />

    <ul>
      <li class="clickable" v-on:click="addLayer()">
        <b>+ Add Layer</b>
      </li>
      <li class="clickable" v-if="selectedLayer !== -1" v-on:click="deleteLayer()">
        <b>&ndash; Delete Selected Layer</b>
      </li>
    </ul>

    <ModalComponent v-show="showAddShape" >
      <AddShapeMenu modal :layer="selectedLayer" v-on:close="showAddShape=false" />
    </ModalComponent>

  </div>

</template>

<script>
  import {dataBus} from "../../main";
  import ModalComponent from "./ModalComponent";
  import AddShapeMenu from "./AddShapeMenu";

  /**
   * This component renders the layer menu.
   * It can react to user interaction by sending one of the following events over the {@link dataBus}:
   * * "addLayer"
   * * "deleteLayer"
   * * "editLayer"
   * * "deleteShape"
   *
   * It can also access the {@link AddShapeMenu} through a {@link ModalComponent}.
   */
  export default {
    name: "LayerMenu",
    components: {AddShapeMenu, ModalComponent},
    data() {
      return {
        layers: [],
        selectedLayer: -1,
        selectedShape: -1,
        showAddShape: false
      }
    },
    methods: {
      layerName: function(index) {
        if (index === -1) {
          return "Canvas";
        }
        else {
          return "Layer " + index;
        }
      },
      selectLayer: function(index) {
        if (this.selectedLayer !== index) {
          this.selectedLayer = index;
          if (index === -1) {
            this.selectedShape = -1;
          }
          else {
            this.selectedShape = this.layers[this.selectedLayer].shapes.length - 1;
          }
          this.layersUpdated();
        }
      },
      selectShape: function(index) {
        if (this.selectedShape !== index) {
          this.selectedShape = index;
          this.layersUpdated();
        }
      },
      toggleVisibility: function(index) {
        this.layers[index].visible = !this.layers[index].visible;
        this.editLayer(index);
      },
      updateLayers: function(canvas) {
        // Switch to last layer if one has been created
        // If creation of a layer ever puts the new layer somewhere else than the last index, update this!
        if (canvas.layers.length > this.layers.length) {
          this.selectedLayer = canvas.layers.length - 1;
          this.selectedShape = canvas.layers[this.selectedLayer].shapes.length - 1;
        }
        // Switch to last layer if deletion of a layer caused the currently selected one to go out of bounds
        // In that case, select the last shape of that layer
        else if (canvas.layers.length < this.layers.length) {
          if (canvas.layers.length <= this.selectedLayer) {
            this.selectedLayer = canvas.layers.length - 1;
          }
          if (this.selectedLayer === -1) {
            this.selectedShape = -1;
          }
          else {
            this.selectedShape = canvas.layers[this.selectedLayer].shapes.length - 1;
          }
        }
        // If Layers have not been modified, perform similar checks for the selected Shape
        else if (this.selectedLayer !== -1) {
          let newShapesLength = canvas.layers[this.selectedLayer].shapes.length;
          let oldShapesLength = this.layers[this.selectedLayer].shapes.length;
          if (newShapesLength > oldShapesLength || newShapesLength <= this.selectedShape) {
            this.selectedShape = canvas.layers[this.selectedLayer].shapes.length - 1;
          }
        }

        // Update layers and shapes
        this.layers = canvas.layers;
        dataBus.$emit('canvasUpdated', {
          width: canvas.width,
          height: canvas.height
        });
        this.layersUpdated();
      },
      layersUpdated: function() {
        dataBus.$emit('layersUpdated', {
          layers: this.layers,
          selectedLayer: this.selectedLayer,
          selectedShape: this.selectedShape
        });
      },
      addLayer: function() {
        dataBus.$emit('addLayer', {});
      },
      editLayer: function(layerIndex) {
        dataBus.$emit('editLayer', {
          layerIndex: layerIndex,
          visible: this.layers[layerIndex].visible
        });
      },
      deleteLayer: function() {
        dataBus.$emit('deleteLayer', {
          layerIndex: this.selectedLayer
        });
      },
      deleteShape: function() {
        dataBus.$emit('deleteShape', {
          layerIndex: this.selectedLayer,
          shapeIndex: this.selectedShape
        })
      }
    },
    created: function() {
      dataBus.$on('response', (response) => this.updateLayers(response.body.canvas));
    }
  }
</script>

<style scoped>

  .layer-menu-grid {
    height: 100%;
    width: 100%;
  }

</style>
