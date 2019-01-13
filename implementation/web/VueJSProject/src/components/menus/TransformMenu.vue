<template>
  <div class="add-shape-menu">

    <label>Transformation to apply: </label>
    <select v-model="transformationType">
      <option value="TRANSLATE">Move</option>
      <option value="ROTATE">Rotate</option>
      <option value="SCALE">Scale</option>
      <option value="UNIFORM_SCALE">Scale (Uniform)</option>
      <option value="SKEW">Skew</option>
    </select>

    <hr />

    <ul v-if="transformationType!=='TRANSLATE'">
      <li>
        Transformation Origin:
        <br />
        <label>x: </label>
        <input type="text" v-model="originX" />
        <label>y: </label>
        <input type="text" v-model="originY" />
      </li>
    </ul>

    <ul v-if="transformationType==='TRANSLATE'">
      <li>
        Move by:
        <br />
        <label>x: </label>
        <input type="text" v-model="translateX" />
        <label>y: </label>
        <input type="text" v-model="translateY" />
      </li>
    </ul>

    <ul v-if="transformationType==='ROTATE'">
      <li>
        <label>Rotate by angle (in degrees):</label>
        <br />
        <input type="text" v-model="rotationAngle" />
      </li>
    </ul>

    <ul v-if="transformationType==='SCALE'">
      <li>
        Scale by factors:
        <br />
        <label>x: </label>
        <input type="text" v-model="scaleX" />
        <label>y: </label>
        <input type="text" v-model="scaleY" />
      </li>
    </ul>

    <ul v-if="transformationType==='UNIFORM_SCALE'">
      <li>
        <label>Scale by factor:</label>
        <br />
        <input type="text" v-model="scaleX" />
      </li>
    </ul>

    <ul v-if="transformationType==='SKEW'">
      <li>
        <label>Skew by angle (in degrees): </label>
        <input type="text" v-model="skewAngle" />
        <label>Skew axis: </label>
        <select v-model="skewAxis">
          <option value="x">X</option>
          <option value="y">Y</option>
        </select>
      </li>
    </ul>

    <hr />

    <ul :class="{'modal-list': modal}">
      <li class="confirm clickable" v-on:click="transform()">
        Confirm
      </li>
    </ul>

    <hr />

    <ul v-if="modal" class="modal-list">
      <li class="cancel clickable" v-on:click="close()">
        Cancel
      </li>
    </ul>

  </div>
</template>

<script>
  import {dataBus} from "../../main";

  /**
   * This component renders the add shape menu.
   * Upon confirmation, it sends an "addShape" event over the {@link dataBus}.
   */
  export default {

    name: "TransformMenu",

    props: {
      modal: Boolean,
      elementId: Number
    },

    data() {
      return {
        transformationType: "TRANSLATE",
        originX: 0,
        originY: 0,
        translateX: 0,
        translateY: 0,
        rotationAngle: 0,
        scaleX: 0,
        scaleY: 0,
        skewAngle: 0,
        skewAxis: "x"
      }
    },

    methods: {

      transform: function() {
        switch(this.transformationType) {

          case "TRANSLATE":
            dataBus.$emit('transformElement', {
              elementID: this.elementId,
              transform: {
                transformationType: this.transformationType,
                origin: {
                  x: this.originX,
                  y: this.originY
                },
                translation: {
                  x: this.translateX,
                  y: this.translateY
                }
              }
            });
            break;

          case "ROTATE":
            dataBus.$emit('transformElement', {
              elementID: this.elementId,
              transform: {
                transformationType: this.transformationType,
                origin: {
                  x: this.originX,
                  y: this.originY
                },
                rotationAngle: this.rotationAngle
              }
            });
            break;

          case "SCALE":
            dataBus.$emit('transformElement', {
              elementID: this.elementId,
              transform: {
                transformationType: this.transformationType,
                origin: {
                  x: this.originX,
                  y: this.originY
                },
                scale: {
                  x: this.scaleX,
                  y: this.scaleY
                }
              }
            });
            break;

          case "UNIFORM_SCALE":
            dataBus.$emit('transformElement', {
              elementID: this.elementId,
              transform: {
                transformationType: this.transformationType,
                origin: {
                  x: this.originX,
                  y: this.originY
                },
                scale: this.scaleX
              }
            });
            break;

          case "SKEW":
            dataBus.$emit('transformElement', {
              elementID: this.elementId,
              transform: {
                transformationType: this.transformationType,
                origin: {
                  x: this.originX,
                  y: this.originY
                },
                skewAngle: this.skewAngle,
                skewAxis: this.skewAxis
              }
            });
            break;

          default:
            console.log("Unkown transformation");

        }

        if (this.modal) {
          this.close();
        }
      },


      close: function() {
        this.$emit('close');
      }
    }

  }

</script>

<style scoped>

</style>
