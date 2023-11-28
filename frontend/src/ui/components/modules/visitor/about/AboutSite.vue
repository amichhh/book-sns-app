<script setup lang="ts">
import { mdiCommentQuestion } from '@mdi/js';
</script>

<template>
  <v-row dense>
    <v-col class="title ml-4 mt-2">
      <v-icon size="large" :icon="mdiCommentQuestion" class="mb-1"></v-icon>
      share book! とは
    </v-col>
  </v-row>
</template>

<style scoped>
.title {
  font-size: 22px;
  font-weight: 900;
}
</style>