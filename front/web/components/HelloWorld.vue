<template>
  <div class="hello">
    <h1>{{ msg }}</h1>

    <div>
      <h2> Adicione seu arquivo aqui</h2>
      <input type="file" id="upload">
      <button @click="submitCNAB"> Submit </button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class HelloWorld extends Vue {
  @Prop() private msg!: string;

  private stores: Array<object> = []

  submitCNAB = () => {
    console.log('Submit...');
  }

  mounted() {
    this.stores = [];
    alert('Mounted...');
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  h3 {
    margin: 40px 0 0;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>
