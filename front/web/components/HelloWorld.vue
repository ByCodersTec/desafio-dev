<template>
  <div class="content-box">
    <h1>{{ msg }}</h1>

    <div class="content-area">
      <h2> Adicione seu arquivo aqui</h2>
      <div class="upload_box">
        <span @click="$refs.file.click()" v-if="!uploadFile"> click here to upload</span>
        <div v-else>
          <span @click="uploadFile=null" class="x-button"> X </span>
          <span> File name: {{ uploadFile.name }} </span>
        </div>

        <input type="file" id="upload" ref="file" @input="changeInputFile" accept="text/plain" style="display: none">
        <button @click="submitCNAB" :disabled="!uploadFile"> Submit </button>
      </div>

      <h2> Lista de Lojas e saldo</h2>

      <div v-if="storeId" @click="listEntries">
        Loja - {{ storeId }} - Value = {{ totalValue }}
      </div>

      <div class="store-header">
        <span> Store Name </span>
        <span> Value </span>
      </div>

      <div v-for="item in stores" :key="item.id" class="store-body">
        <div class="store-name">
          <span>
            # {{ item.id }} - {{ item.storeName }}
          </span>
        </div>

        <div class="value">
          <span>
            R$ {{ item.value }}
          </span>
        </div>

        <div class="actions" @click="openStore(item.id)">
          Button
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import byCoderService from '@/services/bycoders';
import ResponseData from '@/types/ResponseData';
import Cnab from '@/types/Cnab';

@Component
export default class HelloWorld extends Vue {
  @Prop() private msg!: string;

  private uploadFile: null | File = null;

  private message?: string = undefined;

  private stores: Array<Cnab> = [];

  private storeId = 0;

  get totalValue() {
    const valueSum = this.stores.reduce((sum, item) => sum + item.value, 0);
    return valueSum;
  }

  changeInputFile(e: any) {
    const file = e.target.files || e.dataTransfer.files;

    // eslint-disable-next-line prefer-destructuring
    this.uploadFile = file[0];
  }

  submitCNAB(): Promise<any> {
    if (!this.uploadFile) {
      // eslint-disable-next-line prefer-promise-reject-errors
      return Promise.reject(false);
    }

    return byCoderService.upload(this.uploadFile).then(() => {
      this.uploadFile = null;
      this.message = 'Successfully uploaded';
      return this.listEntries();
    });
  }

  listEntries(): Promise<any> {
    return byCoderService.listEntries().then((response: ResponseData) => {
      this.stores = response.data;
      this.storeId = 0;
      return true;
    });
  }

  openStore(storeId: number): Promise<any> {
    this.storeId = storeId;
    return byCoderService.listStoreTransactions(storeId).then((response: ResponseData) => {
      this.stores = response.data;
      return true;
    });
  }

  mounted() {
    this.listEntries().catch((error) => {
      // console.log(error);
    });
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

  .content-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    &.content-area {
      width: 80%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
  }

  .upload_box {
    width: 32rem;
    height: 3rem;
    display: flex;
    justify-content: space-around;
    align-items: center;
    text-align: center;

    border-width: 1px;
    border-style: solid;
    border-color: darkgrey;
    cursor: pointer;
  }

  .x-button {
    border-width: 1px;
    border-style: solid;
    border-color: red;

    &:hover {
      background-color: red;
      border-color: darkgrey;
    }
  }

  .store-header {
    width: 100%;
    display: flex;
    justify-content: space-around;

    border-width: 1px;
    border-style: solid;
    border-color: darkgrey;
  }

  .store-body {
    width: 100%;
    display: flex;
    justify-content: space-between;

    border-width: 1px;
    border-style: solid;
    border-color: darkgrey;

    //background-color: lightgrey;
    &:nth-child(even) {
      background-color: antiquewhite;
    }

    .store-name {
      width: 50%;
      display: flex;
      justify-content: flex-start;
    }

    .value {
      width: 30%;
      display: flex;
      justify-content: flex-start;
    }

    .actions {
      width: 20%;
      display: flex;
      justify-content: center;
    }
  }
</style>
