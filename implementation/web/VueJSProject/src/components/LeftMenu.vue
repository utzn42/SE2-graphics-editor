<template>
  <layer-menu />
</template>

<script>
  import LayerMenu from "./menus/LayerMenu";

  /**
   * This component renders the left menu.
   * It contains the {@link LayerMenu} component.
   */
  export default {

    name: "LeftMenu",

    components: {
      LayerMenu
    }

  }
</script>

<style scoped>

</style>
