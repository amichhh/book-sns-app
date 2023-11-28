<script setup lang="ts">
import { findBookOrderByRecordCount } from '@/api/visitor/book';
import { findLikeRecord } from '@/api/visitor/record';
import type { Book, Record } from '@/types';
import RecordLike from '@/ui/components/modules/visitor/mypage/RecordLike.vue';
import { onMounted, ref } from 'vue';

const books = ref<Book[]>([])
const searchBook = async () => {
  books.value = await findBookOrderByRecordCount()
}

const records = ref<Record[]>([])
const searchRecord = async () => {
  records.value = await findLikeRecord()
}

onMounted(() => {
  searchBook()
  searchRecord()
})
</script>

<template>
  <v-container>
    <RecordLike
      :records="records"
      :books="books"
    />
  </v-container>
</template>