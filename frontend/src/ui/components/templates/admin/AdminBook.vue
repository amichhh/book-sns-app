<script setup lang="ts">
import { findBook } from '@/api/visitor/book';
import type { Book } from '@/types';
import BookDetail from '@/ui/components/modules/admin/book/BookDetail.vue';
import BookRegister from '@/ui/components/modules/admin/book/BookRegister.vue';
import BookSearch from '@/ui/components/modules/admin/book/BookSearch.vue';
import BookTable from '@/ui/components/modules/admin/book/BookTable.vue';
import { onMounted, ref } from 'vue';

const books = ref<Book[]>([])
const filteredBooks = ref<Book[]>([])

const selected = ref<Book>()
const open = ref<boolean>(false)

const searchBook = async () => {
  books.value = await findBook()
  filteredBooks.value = await findBook()
}

onMounted(() => {
  searchBook()
})
</script>

<template>
  <v-container fluid>
    <BookDetail
      :open="open"
      :book="selected"
      @close="open = false"
    />
    <v-row dense>
      <v-col cols="8">
        <BookSearch
          :books="books"
          @filter="(filtered: Book[]) => { filteredBooks = filtered }"
        />
      </v-col>
      <v-col class="text-right mt-1">
        <BookRegister />
      </v-col>
    </v-row>
    <v-row dense>
      <v-col>
        <BookTable
          :books="filteredBooks"
          @select="(item: Book) => { selected = item; open = true; }"
        />
      </v-col>
    </v-row>
  </v-container>
</template>