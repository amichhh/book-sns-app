<script setup lang="ts">
import { findBook } from '@/api/visitor/book';
import { findPublicRecord } from '@/api/visitor/record';
import type { Book, Record } from '@/types';
import RecordDetail from '@/ui/components/modules/admin/record/RecordDetail.vue';
import RecordSearch from '@/ui/components/modules/admin/record/RecordSearch.vue';
import RecordTable from '@/ui/components/modules/admin/record/RecordTable.vue';
import { onMounted, ref } from 'vue';

const records = ref<Record[]>([])
const filteredRecords = ref<Record[]>([])

const selected = ref<Record>()
const open = ref<boolean>(false)

const searchRecord = async () => {
  records.value = await findPublicRecord()
  filteredRecords.value = await findPublicRecord()
}

const books = ref<Book[]>([])
const searchBook = async () => {
  books.value = await findBook()
}

onMounted(() => {
  searchRecord()
  searchBook()
})
</script>

<template>
  <v-container fluid>
    <RecordDetail 
      :open="open"
      :record="selected"
      @close="open = false"
    />
    <v-row dense>
      <v-col>
        <RecordSearch
          :records="records"
          @filter="(filtered: Record[]) => { filteredRecords = filtered }"
        />
      </v-col>
    </v-row>
    <v-row dense>
      <v-col>
        <RecordTable
          :records="filteredRecords"
          :books="books"
          @select="(item: Record) => { selected = item; open = true; }"
        />
      </v-col>
    </v-row>
  </v-container>
</template>