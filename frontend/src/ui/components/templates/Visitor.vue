<script setup lang="ts">
import Footer from '@/ui/components/modules/visitor/Footer.vue';
import Header from '@/ui/components/modules/visitor/Header.vue';
</script>

<template>
  <Header></Header>
  <v-main class="main">
    <RouterView />
  </v-main>
  <Footer></Footer>
</template>

<style scoped>
.main {
  background-color: #efefef;
}
</style>