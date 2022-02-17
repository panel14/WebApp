<template>
  <span class="errMessage">{{ message }}</span>
</template>

<script>
export default {
  name: "ErrorMessage",
  props: ['message'],
}
</script>

<style scoped>
  .errMessage {
    color: #8a0404;
    font-size: 20px;
  }
</style>