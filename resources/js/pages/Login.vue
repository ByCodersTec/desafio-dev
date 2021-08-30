<template>
  <div class="login">
    <div class="titulo">
      <h1>Desafio - ByCordersTech</h1>
    </div>
    <div class="form_box">
      <div class="form">
        <input type="email" v-model="form.username" placeholder="E-mail" />
        <input type="password" v-model="form.password" placeholder="Password" />
        <button @click="login">Entrar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
        grant_type: "password",
        client_id: 1,
        client_secret:
          "3415f8a09480dbc8af2eefacf3347469eb5e3ec3d343085ae514bec7b33c5dcb80de0dcb",
      },
    };
  },

  methods: {
    login() {
      axios.post("http://localhost/oauth/token", this.form).then((response) => {
        if (response.status == 200) {
          this.$store.dispatch("auth/auth", response.data.access_token);
          this.$store.dispatch("auth/isLoged", true);
          this.$router.push({ name: "movimentacao_financeira" });
        } else {
          alert("Credenciais inválidas");
        }
      });
    },
  },
};
</script>

<style scoped>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.titulo {
  margin-top: 10%;
  color: #000;
}

.form_box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 1px solid #000;
  width: 400px;
  height: 200px;
  box-shadow: 2px 1px 1px 1px #000;
  margin-top: 25px;
}

.form {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 15px;
}

input {
  height: 30px;
  margin: 10px;
  border: none;
  border-bottom: 2px solid #333;
}

button {
  margin-top: 15px;
  height: 30px;
  background-color: rgb(68, 5, 68);
  color: #fff;
}
</style>