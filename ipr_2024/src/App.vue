<template>
  <upper-bar></upper-bar>
  <div class="base">
    <router-view/>
  </div>
</template>

<script>
import UpperBar from "@/components/upperBar.vue";
export default {
  components: {UpperBar}

}

</script>

<style>

</style>
