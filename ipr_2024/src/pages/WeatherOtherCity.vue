<template>
 <div>
   <select v-model="changeCity" @change="changeOption" >
    <option value="Omsk">Омск</option>
     <option v-for="city in citys" :key="city.value" :value="city.value">
       {{ city.name }}
     </option>
   </select>
   <div id="table" class="divTable">
     <div id="upperBlock" class="divRow">
       <div id="dateTime" class="divCell">Дата и время</div>
       <div id="temperature" class="divCell">Температура</div>
       <div id="press" class="divCell">Давление</div>
     </div>
       <div v-for="tempData in temperatures.data" :key="tempData.time" class="divRow">
         <div id="dateTime" class="divCell">{{ tempData.time }}</div>
         <div id="temperature" class="divCell">{{ tempData.temp }}</div>
         <div id="press" class="divCell">{{tempData.pres}}</div>
       </div>
   </div>
 </div>
</template>

<script>
import axios from "axios";

export default {

  data() {
    return {

      citys:
          [{
            "value": "Krasnoyarsk",
            "name": "Красноярск"
          },
            {
              "value": "Novosibirsk",
              "name": "Новосибирск"
            }],
      changeCity: "Omsk",
      temperatures: []
    }
  },
  name: "weatherOtherCity",
  props: {
    modelValue: {
      type: String
    },
    options: {
      type: Array,
      default: () => []
    }
  },
  methods: {
    async changeOption() {
      const data = new Date();
      const jsonData = data.toJSON().split('T')[0];
      const options = {
        method: 'GET',
        url: 'https://meteostat.p.rapidapi.com/stations/hourly',
        params: {
          station: '28382',
          start: jsonData,
          end: jsonData,
          tz: 'Asia/' + this.changeCity
        },
        headers: {
          'X-RapidAPI-Key': '77aeeed127msh4f7226094c0ad44p14c306jsnadb88858a947',
          'X-RapidAPI-Host': 'meteostat.p.rapidapi.com'
        }
      };

      try {
        const response = await axios.request(options);
        console.log(response.data);
        this.temperatures = response.data
      } catch (error) {
        console.error(error);
      }
    }
  },
  mounted() {
    this.changeOption()
  }
}
</script>

<style scoped>

.divTable
{
  margin: auto;
  display:  table;
  width:auto;
  background-color:#eee;
  border:1px solid  #666666;
  border-spacing:5px;
}

.divRow
{
  display:table-row;
  width:auto;
}

.divCell
{
  float:left;/*fix for  buggy browsers*/
  display:table-column;
  width:200px;
  background-color:#ccc;
}
</style>