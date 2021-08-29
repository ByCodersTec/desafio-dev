<template>
  <div class="movimentacao_financeira">
    <nav class="titulo">
      <h1>Desafio - ByCodersTech</h1>
    </nav>

    <div class="form">
      <form>
        <input @change="fileUpload" type="file" />
        <button @click="importarDados()">Enviar</button>
      </form>
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
        .get("http://localhost/api/movimentacao_financeira")
        .then((response) => {
          this.movimentacao_financeira = response.data.movimentacao_financeira;
        });
    },

    importarDados() {
      let formData = new FormData();
      formData.append("cnab", this.file);

      axios
        .post("http://localhost/api/movimentacao_financeira", formData)
        .then((response) => {
            $this.movimentacao_financeira = response.data.movimentacao_financeira;
        });
    },

    fileUpload(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
    
      this.file = files[0]
    },
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
  background-color: rgb(68, 5, 68);
  border-radius: 5px;
  color: #fff;
  height: 70px;
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
}
</style>
