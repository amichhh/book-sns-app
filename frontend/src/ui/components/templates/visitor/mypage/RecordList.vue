<script setup lang="ts">
import { findBookOrderByRecordCount } from '@/api/visitor/book';
import { findRecord } from '@/api/visitor/record';
import type { Book, Record } from '@/types';
import RecordList from '@/ui/components/modules/visitor/mypage/RecordList.vue';
import { onMounted, ref } from 'vue';

const books = ref<Book[]>([])
const searchBook = async () => {
  books.value = await findBookOrderByRecordCount()
}

const records = ref<Record[]>([])
const searchRecord = async () => {
  records.value = await findRecord()
}

onMounted(() => {
  searchBook()
  searchRecord()
})
</script>

<template>
  <v-container>
    <RecordList
      :records="records"
      :books="books"
    />
  </v-container>
</template>