<template>
  <div class="layer-menu-grid">
    <div class="layer-menu-shapes">
      <ul v-if="selectedLayer !== -1">
        <li v-for="(shape, index) in layers[selectedLayer].shapes" :class="{selected: index === selectedShape}" v-on:click="selectShape(index)">
          Shape {{index}} ({{shape.shape}})
        </li>
        <li v-if="selectedShape !== -1" v-on:click="deleteShape()">
          - Delete Selected Shape
        </li>
      </ul>
    </div>
    <div class="layer-menu-layers">
      <ul>
        <li v-on:click="selectLayer(-1)" :class="{selected: selectedLayer === -1}">
          {{layerName(-1)}}
        </li>
        <li v-for="(layer, index) in layers" :class="{selected: index === selectedLayer}" v-on:click="selectLayer(index)">
          {{layerName(index)}}
        </li>
        <li v-on:click="addLayer()">
          + Add Layer
        </li>
        <li v-if="selectedLayer !== -1" v-on:click="deleteLayer()">
          - Delete Selected Layer
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  import {dataBus} from "../../main";

  export default {
    name: "LayerMenu",
    data() {
      return {
        layers: [],
        selectedLayer: -1,
        selectedShape: -1
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
        this.selectedLayer = index;
        if (index === -1) {
          this.selectedShape = -1;
        }
        else {
          this.selectedShape = this.layers[this.selectedLayer].shapes.length - 1;
        }
        this.layersUpdated();
      },
      selectShape: function(index) {
        this.selectedShape = index;
        this.layersUpdated();
      },
      updateLayers: function(canvas) {
        this.layers = canvas.layers;
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
    background-color: #555555;
  }

  .layer-menu-grid {
    display: grid;
    grid-template-columns: auto;
    grid-template-rows: 50% 50%;
    height: 100%;
    width: 100%;
    background-color: darkgray;
  }

  .layer-menu-shapes {
    grid-column: 1;
    grid-row: 1;
    overflow-y: auto;
  }

  .layer-menu-layers {
    grid-column: 1;
    grid-row: 2;
    overflow-y: auto;
  }

</style>
