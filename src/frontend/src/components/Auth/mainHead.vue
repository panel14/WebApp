<template>
    <header>{{headerMes}}</header>
</template>

<script>
export default {
  name: 'Head',
  data() {
    return{
      headerMes: 'Верзаков Александр Юрьевич, P3232, 312434'
    }
  }
}
</script>

<style scoped>
  header {
    font-size: 20px;
    background: #404040;
    width: 100%;
    height: 40px;
    top: 0.75%;
  }
  @media (max-width: 706px) {
    header {
      height: auto;
      text-align: center;
    }
  }
</style>
