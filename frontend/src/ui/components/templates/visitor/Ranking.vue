<script setup lang="ts">
import { findBookOrderByRecordCount } from '@/api/visitor/book';
import type { Book } from '@/types';
import Ranking from '@/ui/components/modules/visitor/ranking/Ranking.vue';
import { onMounted, ref } from 'vue';

const books = ref<Book[]>([])
const searchBook = async () => {
  books.value = await findBookOrderByRecordCount()
}

onMounted(() => {
  searchBook()
})
</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="7">
        <Ranking :books="books"/>
      </v-col>
      <v-col cols="3"></v-col>
      <v-col cols="1"></v-col>
    </v-row>  
  </v-container>
</template>