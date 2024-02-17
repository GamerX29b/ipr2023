<template>
  <div>
    Это главная страница.
    Существует для того чтобы показать что переключение по вкладкам работает.

    {{ today.getFullYear() }}-{{ today.getMonth() }}-{{ today.getDay() }}
  </div>
</template>

<script>
export default {
  name: "Central-page",


  data () {
    return {
      today: new Date(),
    };
  }
}

</script>

<style scoped>

</style>