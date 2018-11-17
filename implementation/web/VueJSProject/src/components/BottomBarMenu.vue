<template>
  <div class="bar-menu">

    <button v-on:click="showDownloadMenu(true)">Download</button>

    <modal-component v-show="downloadMenuVisible">
      <download-menu v-on:close="showDownloadMenu(false)" />
    </modal-component>

  </div>
</template>

<script>
  import ModalComponent from "./menus/ModalComponent";
  import DownloadMenu from "./menus/DownloadMenu";

  /**
   * This component renders the bottom bar menu.
   * It contains the {@link DownloadMenu}, wrapped in a {@link ModalComponent}.
   */
  export default {

    name: "BottomBarMenu",

    components: {
      DownloadMenu,
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
