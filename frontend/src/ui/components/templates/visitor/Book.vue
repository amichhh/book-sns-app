<script setup lang="ts">
import router from '@/router';
import { useStore } from '@/stores';
import type { RakutenBook } from '@/types';
import Login from '@/ui/components/modules/Login.vue';
import BookSearch from '@/ui/components/modules/visitor/book/BookSearch.vue';
import { reactive, ref } from 'vue';

const store = useStore()

const dialog = reactive({
  login: false
})

const book = ref<RakutenBook>()

const checkLogin = () => {
  if (!store.logined) {
    dialog.login = true
  }
}

const moveToRecordWrite = () => {
  if (store.logined) {
    store.book = book.value
    router.push('/mypage/record/write')
  }
}

</script>

<template>
  <v-container fluid>
    <Login
      :open="dialog.login"
      @close="dialog.login = false; moveToRecordWrite();"
    />
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="7">
        <BookSearch 
          @write="(item: RakutenBook) => { book = item; checkLogin(); moveToRecordWrite(); }"
        />
      </v-col>
      <v-col cols="2"></v-col>
      <v-col cols="1"></v-col>
    </v-row>
  </v-container>
</template>