<template>
  <div>
    <div @click="toMain()"></div>
    <div>
      <button @click="toMain">main</button>
      <button @click="toHere">Страница текущей погоды</button>
      <button @click="toOther">Погода в разных городах</button>
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

</style>