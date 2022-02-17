<template>
  <div class="mainContainer">
    <div class="inData">
      <div class="formContainer">
        <form @submit.prevent ="onSubmit">
          <span class="formHeader">Введите данные:</span><br>
          X:
          <label v-for="value in values">
            <input type="radio" v-model="xValue" v-bind:value="value.num" name="xValues"/>
            {{ value.num }}
          </label><br>
          Y:
          <input type="text" placeholder="(-5; 5)" v-model="yValue" maxlength="5" id="yVal"/><br>
          R:
          <label v-for="value in values">
            <input type="radio" v-model="rValue" v-bind:value="value.num" v-on:change="changeGraph" name="yValues"/>
            {{ value.num }}
          </label><br>
          <button id="sendButton">Отправить</button><br>
          <p v-if="errors.length">
            <span v-for="error in errors">{{ error }}<br></span>
          </p>
        </form>
      </div>
      <div class="graph">
        <Graph ref="graph" v-on:transPoint="getDataFromGraph" v-on:invalidRadius
            ="showError"></Graph>
      </div>
    </div>
    <div class="dataTable">
      <table>
        <caption>Результаты</caption>
        <tr v-for="row in rows">
          <th>{{ row.x }}</th>
          <th>{{ row.y }}</th>
          <th>{{ row.r }}</th>
          <th>{{ row.result }}</th>
          <th>{{ row.now }}</th>
          <th>{{ row.workTime }}</th>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import Graph from "@/components/mainPage/Graph";


export default {
  name: "mainPage",
  components: {Graph},
  async mounted() {
    const data = (await this.$api.auth.getPoints()).data
      data.points.forEach((elem)=>{
        this.rows.push(elem)
      })
  },
  data(){
    return {
      values: [
        { num: '-5'},
        { num: '-4'},
        { num: '-3'},
        { num: '-2'},
        { num: '-1'},
        { num: '0'},
        { num: '1'},
        { num: '2'},
        { num: '3'}
      ],
      xValue: null,
      yValue: null,
      rValue: null,
      errors: [],
      rows: [
        { x: 'X', y: 'Y', r: 'R', result: 'Попадание', now: 'Время', workTime: 'Время обработки' }
      ]
    }
  },
  methods: {
    validValues() {
      this.errors = []

      if (!this.yValue || !this.xValue || !this.rValue)
        this.errors.push('Не все поля заполнены')
      let re = /^-?[0-9]*[.]?[0-9]+$/

      if (!re.test(this.yValue))
        this.errors.push('Поле Y содержит недопустимые символы')
      const yFloat = parseFloat(this.yValue)

      if (yFloat > 5 || yFloat < -5)
        this.errors.push('Значение Y выходит за пределы допустимого диапазона')

      if (parseFloat(this.rValue) < 0)
        this.errors.push('Значение R меньше или равно 0')
      return !this.errors.length;
    },
    pack() {
      return {
        x: this.xValue,
        y: this.yValue,
        r: this.rValue
      }
    },
    addRow(payload){
      this.rows.push(payload)
    },
    changeGraph(){
      this.errors = []
      this.$refs.graph.changeGraph(this.rValue)
      this.readPoints()
    },
    getDataFromGraph(data){
      console.log(data.point)
      this.addRow(data.point)
    },
    readPoints(){
      this.rows.forEach((elem) => {
        if (elem.r === parseInt(this.rValue)){
          this.printPoint(elem)
        }
      })
    },
    printPoint(point){
      const coords = [point.x, point.y, point.r]
      const result = point.result
      this.$refs.graph.printPoint(coords, result)
    },
    showError(message){
      this.errors = []
      this.errors.push(message)
    },
    async onSubmit() {
      if (this.validValues()){
        try{
          const data = (await this.$api.auth.sendPoint(this.pack())).data
          this.addRow(data.point)
          this.printPoint(data.point)
        }
        catch (error) {
          this.showError(error.data.message)
        }
      }
    }
  }
}
</script>

<style>

  .mainContainer {
    display: flex;
  }

  .inData {
    text-align: center;
    margin: 10px 0 0 10px;
  }

  .graph {
    width: auto;
    top: 10px;
  }

  .dataTable {
    width: 60%;
    margin-left: auto;
  }

  .formHeader {
    font-size: 20px;
  }

  canvas {
    border: solid 2px black;
    margin: 20px 0 0 0 ;
    background-color: #2a2a2a;
  }

  #yVal {
    width: 315px;
    margin: 10px 0 10px 0;
  }

  #sendButton {
    margin: 15px;
    width: 315px;
  }

  table {
    font-size: 20px;
    border-radius: 10px;
    border-spacing: 0;
    text-align: center;
    width: 100%;
  }
  th {
    background: #86a4ee;
    color: #605f5f;
    padding: 2px 5px;
  }
  th {
    border-style: solid;
    border-width: 0 1px 1px 0;
    border-color: white;
  }
  th:first-child {
    border-top-left-radius: 10px;
  }
  th:last-child {
    border-top-right-radius: 10px;
    border-right: none;
  }
  tr:last-child td:first-child {
    border-radius: 0 0 0 10px;
  }
  tr:last-child td:last-child {
    border-radius: 0 0 10px 0;
  }
  tr td:last-child {
    border-right: none;
  }
  caption {
    font-size: 25px;
    color: #484646;
    padding: 5px 20px;
    background-color: #026ee7;
    border-radius: 10px;
    border-spacing: 10px;
  }
  th:hover {
    background: #c6d1f5;
  }

  @media (max-width: 917px) {
    .mainContainer{
      display: block;
    }
    .formContainer {
      float: left;
      width: 100%;
    }
    .dataTable {
      float: left;
      clear: left;
      width: 100%;
    }
    .graph {
      float: left;
      clear: left;
      width: 100%;
    }
  }
</style>