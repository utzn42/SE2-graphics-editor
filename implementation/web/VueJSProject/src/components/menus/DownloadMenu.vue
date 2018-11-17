<template>
  <div class="download-menu">

    <label>Please select the desired image format: </label>
    <select v-model="fileType">
      <option value="svg">SVG</option>
      <option value="png">PNG</option>
      <option value="jpg">JPG</option>
    </select>

    <br />

    <button v-on:click="download()">Download image</button>
    <button v-on:click="close()">Cancel</button>

  </div>
</template>

<script>
  import {dataBus} from "../../main";

  /**
   * This component renders the download menu.
   * Upon confirmation, it sends a "download" event over the {@link dataBus}.
   */
  export default {

    name: "DownloadMenu",

    data() {
      return {
        fileType: "svg"
      }
    },

    methods: {

      download: function() {
        dataBus.$emit('download', this.fileType);
        this.close();
      },

      close: function() {
        this.$emit('close');
      }

    }

  }
</script>

<style scoped>

</style>
