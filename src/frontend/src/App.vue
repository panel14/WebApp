<template>
  <Navigation/>
  <router-view/>
</template>
<script>

import Navigation from "@/components/Auth/Navigation";

export default {
  components: {Navigation}
}
</script>

<style >
  form {
    box-sizing: border-box;
    font-family: Helvetica sans-serif;
  }
  .mainForm {
    width: 100%;
    margin: 0 auto;
    text-align: center;
  }
  input[type="text"], input[type="password"] {
    width: 600px;
    padding: 10px;
    margin: 10px 0;
    border: 0;
    box-shadow: 0 0 15px 2px rgba(0, 0, 0, 0.26);
    border-radius: 10px;
    text-align: center;
  }
  button {
    width: 600px;
    padding: 7px;
    border: none;
    border-radius: 10px;
    background-color: #0366d6;
    color: snow;
  }
  button:hover {
    background-color: #0171f1;
  }

  @media (max-width: 657px) {
    button {
      width: 90%;
    }
    input[type="text"], input[type="password"] {
      width: 90%;
    }

  }

</style>