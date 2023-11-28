<script setup lang="ts">
import type { Book } from '@/types';
import { mdiMagnify } from '@mdi/js';
import { ref } from 'vue';

const props = defineProps<{ books: Book[] }>()
const emit = defineEmits(['filter'])

const keyword = ref<string>("")

const filterBooks = () => {
  const regex = new RegExp(`.*${keyword.value}.*`)
  const filtered = keyword.value ? props.books.filter(v => regex.test(v.title) || regex.test(v.author)): props.books
  emit('filter', filtered)
}
</script>

<template>
  <v-card variant="flat" class="pa-2" color="transparent">
    <v-row dense>
      <v-col cols="10">
        <v-text-field
          v-model="keyword" 
          :prepend-inner-icon="mdiMagnify" 
          placeholder="書籍タイトルまたは著者名で検索" 
          hide-details
          variant="outlined" 
          density="compact" 
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="2">
        <v-btn variant="flat" color="teal" class="ma-1" @click="filterBooks">検索</v-btn>
      </v-col>
    </v-row>
  </v-card>
</template>