<template>
  <div class="bar-menu">
    <button v-on:click="showDownloadMenu(true)">Download</button>
    <modal-component v-show="downloadMenuVisible">
      <modal-menu-download v-on:close="showDownloadMenu(false)" />
    </modal-component>
  </div>
</template>

<script>
  import ModalComponent from "./ModalComponent";
  import ModalMenuDownload from "./ModalMenuDownload";

  export default {
    name: "BarMenu",
    components: {
      ModalMenuDownload,
      ModalComponent,
    },
    data() {
      return {
        downloadMenuVisible: false
      }
    },
    methods: {
      showDownloadMenu: function(show) {
        this.downloadMenuVisible = show;
      }
    }
  }
</script>

<style scoped>

  .bar-menu {
    width: 100%;
    height: 100%;
    display: grid;
    justify-content: center;
    margin: 0;
    padding: 0;
  }

</style>
