<template>
  <div class="shell">
    <div class="mainHeader">
      Верзаков Александр Юрьевич, P3232, 312434
    </div>
    <div class="sideMenu">
      <ul class="linkList">
        <li>
          <span v-if="this.$store.getters.isLoggedIn">Пользователь: {{ this.$store.getters.getUserName }} | </span>
          <router-link to="/login" v-if="this.$store.getters.isLoggedIn" class="rLink"
                       @click="logout">Выйти</router-link>
        </li>
        <li>
          <router-link to="/login" v-if="!this.$store.getters.isLoggedIn" class="rLink">Войти</router-link>
        </li>
        <li>
          <router-link to="/register" v-if="!this.$store.getters.isLoggedIn" class="rLink"> Зарегистрироваться</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import mainHead from '@/components/Auth/mainHead'
export default {
  name: "Navigation",
  components: {mainHead},
  methods: {
    logout() {
      this.$store.dispatch('deleteUser')
    }
  }
}
</script>

<style scoped>
  .shell {
    font-family: Helvetica,sans-serif;
    color: snow;
    background-color: #3f3c3c;
    display: flex;
    flex-flow: row wrap;
    width: 100%;
  }

  .mainHeader {
    font-size: 24px;
    margin: 10px 0 10px 10px;
  }
  .sideMenu {
    margin-left: auto;
  }
  ul> li {
    display: inline;
    padding: 10px;
  }
  .rLink {
    text-decoration: none;
    color: snow;
  }
  .rLink:hover {
    color: #b9b8b8;
  }

  @media (max-width: 841px) {
    .shell{
      text-align: center;
    }
    .sideMenu, .mainHeader {
      margin: auto;
    }
  }
</style>