<template>
  <div class="movimentacao_financeira">
    <div>
      <nav class="titulo">
        <h1>Desafio - ByCodersTech</h1>
      </nav>
      <div class="sair">
        <button @click="sair">Sair</button>
      </div>
      <div class="form">
        <input @change="fileUpload" type="file" />
        <br />
        <button @click="importarDados()">Enviar</button>
      </div>

      <div class="table">
        <h4>Resultados Financeiros</h4>
        <vue-good-table
          :columns="colunas"
          :rows="movimentacao_financeira"
          :pagination-options="{
            enabled: true,
            mode: 'pages',
            perPage: 10,
            nextLabel: 'next',
            prevLabel: 'prev',
          }"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { VueGoodTable } from "vue-good-table";

export default {
  components: {
    VueGoodTable,
  },

  created() {
    this.buscarDados();
  },

  data() {
    return {
      isLoged: false,
      token: "",
      movimentacao_financeira: "",
      colunas: [
        { label: "Dono da Loja", field: "dono_da_loja" },
        { label: "Nome da Loja", field: "nome_da_loja" },
        { label: "Cpf", field: "cpf" },
        { label: "Valor", field: "valor" },
        { label: "Operação", field: "tipo_operacao.descricao" },
        { label: "Cartão", field: "cartao" },
        { label: "Data", field: "data" },
        { label: "Hora", field: "hora" },
      ],
      file: null,
    };
  },

  methods: {
    buscarDados() {
      axios
        .get("http://localhost/api/movimentacao_financeira", {
          headers: {
            Authorization: "Bearer " + this.$store.getters["auth/getToken"],
          },
        })
        .then((response) => {
          this.movimentacao_financeira = response.data.movimentacao_financeira;
        });
    },

    importarDados() {
      let formData = new FormData();
      formData.append("cnab", this.file);

      axios
        .post(
          "http://localhost/api/movimentacao_financeira", formData, {
            headers: {
              Authorization: 'Bearer ' + this.$store.getters['auth/getToken']
            }
          })
        .then((response) => {
          this.movimentacao_financeira = response.data.movimentacao_financeira;
        });
    },

    fileUpload(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;

      this.file = files[0];
    },

    sair() {
      this.$store.dispatch('auth/auth', null)
      this.$store.dispatch('auth/isLoged', false)
      this.$router.push('/')
    }
  },
};
</script>

<style scoped>
.movimentacao_financeira {
  padding: 10px;
}

.titulo {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.form {
  padding: 10px;
  background-color: rgb(68, 5, 68);
  border-radius: 5px;
  color: #fff;
  height: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.sair {
  display: flex;
  flex-direction: row-reverse;
}
button {
  width: 100px;
  height: 20px;
  background-color: #fff;
  border-radius: 5px;
  color: rgb(68, 5, 68);
}
</style>
