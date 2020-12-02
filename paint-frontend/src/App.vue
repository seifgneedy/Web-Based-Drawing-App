<template>
  <div id="app">
    
    <paint  />
  </div>
</template>

<script>
import paint from "./components/paint.vue";

export default {
  name: "App",
  components: {
    paint
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
</style>
