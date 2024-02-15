<template>
  <div class="base">
    <div class="block">Погодное приложение</div>
    <button class="block" @click="tempInfo">Сколько там на улице?</button>
    <div class="block" v-if="visible">Там на улице <strong>{{ temperature }}</strong> градусов и <strong>{{ humidity }}</strong> влажности </div>
    <div class="block" v-else>Нет данных о погоде</div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'WeatherHere',

  components() {

  },
  data () {
    return {
      info: null,
      temperature: 0,
      humidity: 0,
      date: null,
      visible: false

    };
  },
  methods: {
    async tempInfo(){
      try{
        const response = await axios.post('http://localhost:8080/alphaProject-1.0/temperature');
        this.temperature = response.data.temperature;
        this.humidity = response.data.humidity;
        this.date = response.data.timestamp;
        this.visible = true;
      } catch (e) {
        this.visible = false;
        alert('ашипка');
      }
    }
  }
};

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.base {
  margin: 10px 30px 10px 30px;
  border: navy;
  border-radius: 2%;
}
.block {
  margin: 10px 30px 10px 30px;
}
</style>
