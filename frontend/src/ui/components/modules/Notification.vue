<script setup lang="ts">
import { useMessageStore } from '@/stores/message';
import { ref, watch } from 'vue';

const message = useMessageStore()

const show = ref<boolean>(false)

watch(message, () => {
  show.value = true
})
</script>

<template>
  <v-snackbar
    v-model="show"
    location="top"
    :timeout="2000"
    color="teal-lighten-1"
    height="8vh"
    class="mt-7"
  >
    <v-row>
      <v-col class="text-center">{{ message.message }}</v-col>
    </v-row>
  </v-snackbar>
</template>