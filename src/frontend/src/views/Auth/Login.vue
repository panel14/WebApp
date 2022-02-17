<template>
  <div class="mainForm">
    <form @submit.prevent="onSubmit">
      <h2>Вход</h2>

      <input type="text" v-model="login" placeholder="Введите логин"><br>

      <input type="password" v-model="password" placeholder="Введите пароль"><br>

      <button>Войти</button><br>
      <ErrorMessage
          v-bind:message="errToShow"
      ></ErrorMessage>
    </form>
  </div>
</template>

<script>
import ErrorMessage from "@/components/Auth/ErrorMessage";
import store from "@/store/index"

export default {
  name: "Login",
  components: {ErrorMessage},
  data() {
    return {
      login: '',
      password: '',
      errToShow: ''
    }
  },
  methods: {
    pack(){
      return {
        login: this.login,
        password: this.password
      }
    },
    async onSubmit(){
      try{
        const data = (await this.$api.auth.signIn(this.pack())).data
        this.$store.dispatch('setUser',data)
        if (store.getters.isLoggedIn)
          await this.$router.push('/mainPage')
      } catch (error) {
        this.errToShow = error.response.data
      }
    }
  }
}
</script>

<style scoped>

</style>