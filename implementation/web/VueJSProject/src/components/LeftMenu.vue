<template>
  <layer-menu-new />
</template>

<script>
  import LayerMenuNew from "./menus/LayerMenuNew";

  /**
   * This component renders the left menu.
   * It contains the {@link LayerMenu} component.
   */
  export default {

    name: "LeftMenu",

    components: {
      LayerMenuNew
    }

  }
</script>

<style scoped>

</style>
