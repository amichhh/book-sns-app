<script setup lang="ts">
import AdminHeader from '@/ui/components/modules/admin/AdminHeader.vue';
</script>

<template>
  <AdminHeader />
  <v-main class="main">
    <RouterView />
  </v-main>
</template>

<style scoped>
.main {
  background-color: #efefef;
}
</style>