<template>
  <div class="modal-wrapper">
    <div class="modal-content">
      <slot></slot>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ModalComponent",
  }
</script>

<style scoped>

  .modal-wrapper {
    position: fixed;
    display: grid;
    align-items: center;
    justify-content: center;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 998;
    background-color: rgba(0, 0, 0, 0.6);
  }

  .modal-content {
    background-color: #ffffff;
    box-shadow: 2px 2px 20px 1px;
    padding: 10px 15px;
    border-radius: 3px;
  }

</style>
