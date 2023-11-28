<script setup lang="ts">
import { mdiBookAccount } from '@mdi/js';
</script>

<template>
  <v-row dense>
    <v-col class="title ml-4 mt-2">
      <v-icon size="large" :icon="mdiBookAccount" class="mb-1"></v-icon>
      マイページ
    </v-col>
  </v-row>
</template>

<style scoped>
.title {
  font-size: 22px;
  font-weight: 900;
}
</style>