<template>
  <div>
    <div @click="toMain()"></div>
    <div>
      <button class="buttonBlock" @click="toMain">main</button>
      <button class="buttonBlock" @click="toHere">Страница текущей погоды</button>
      <button class="buttonBlock" @click="toOther">Погода в разных городах</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "upperBar",
  methods: {
    toMain() {
      this.$router.push('/')
    },
    toHere() {
      this.$router.push('/weatherHere')
    },
    toOther(){
      this.$router.push('/weatherOtherCity')
    }

  }
}
</script>

<style scoped>
 .buttonBlock {
   background-color: #2c3e50;
   border: 1px solid darkblue;
   color: white;
   padding: 10px 24px;
   cursor: pointer;
   float: left;
   width:33.3%;
 }
 .buttonBlock button:not(:last-child) {
   border-right: none; /* Исключаем двойную рамку */
 }

 .buttonBlock:after {
   content: "";
   clear: both;
   display: table;
 }

 .buttonBlock button:hover {
   background-color: blue;
 }
</style>