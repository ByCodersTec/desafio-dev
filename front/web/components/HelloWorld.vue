<template>
  <div class="hello">
    <h1>{{ msg }}</h1>

    <div>
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

      <div v-for="item in stores" :key="item.id" style="width: 100%">
        <div>
          <span>
            {{ item.id }} - {{ item.value }} - {{ item.storeOwner }}
          </span>
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

  listEntries() {
    return byCoderService.listEntries().then((response: ResponseData) => {
      this.stores = response.data;
    });
  }

  mounted() {
    this.listEntries().then(() => {
      alert('Mounted...');
    }).catch((error) => {
      console.log(error);
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
</style>
