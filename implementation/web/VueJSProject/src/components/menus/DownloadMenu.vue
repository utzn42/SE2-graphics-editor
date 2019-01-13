<template>
  <div class="download-menu">

    <label>Please select the desired image format: </label>
    <select v-model="fileType">
      <option value="SVG">SVG</option>
      <option value="PNG">PNG</option>
      <option value="JPG">JPG</option>
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
        fileType: "SVG"
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
