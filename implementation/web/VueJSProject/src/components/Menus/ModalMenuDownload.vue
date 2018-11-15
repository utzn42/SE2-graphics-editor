<template>
  <div class="download-menu">
    <h3>Please select the desired image format</h3>
    <select v-model="fileType">
      <option value="svg">SVG</option>
      <option value="png">PNG</option>
      <option value="jpg">JPG</option>
    </select>
    <button v-on:click="download()">Download image</button>
  </div>
</template>

<script>
  import {dataBus} from "../../main";

  export default {
    name: "ModalMenuDownload",
    props: {
      show: Boolean
    },
    data() {
      return {
        fileType: "svg"
      }
    },
    methods: {
      download: function() {
        dataBus.$emit('download', {
          fileType: this.fileType
        });
        this.$emit('close');
      }
    }
  }
</script>

<style scoped>

</style>
