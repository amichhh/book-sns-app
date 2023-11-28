<script setup lang="ts">
import { findBookOrderByRecordCount } from '@/api/visitor/book';
import { findMyLikeBind } from '@/api/visitor/likebind';
import { findPublicRecord, likeRecord, unlikeRecord } from '@/api/visitor/record';
import router from '@/router';
import { useStore } from '@/stores';
import { useMessageStore } from '@/stores/message';
import type { Book, LikeBind, Record } from '@/types';
import Login from '@/ui/components/modules/Login.vue';
import BookRanking from '@/ui/components/modules/visitor/top/BookRanking.vue';
import RecordCarousel from '@/ui/components/modules/visitor/top/RecordCarousel.vue';
import { onMounted, reactive, ref } from 'vue';

const store = useStore()
const message = useMessageStore()

const dialog = reactive({
  login: false
})

const books = ref<Book[]>([])
const searchBook = async () => {
  books.value = await findBookOrderByRecordCount()
}

const record = ref<Record>()
const records = ref<Record[]>([])
const searchRecord = async () => {
  records.value = await findPublicRecord()
}

const likebinds = ref<LikeBind[]>([])
const searchLikeBinds = async () => {
  if (!store.logined) {
    return
  }
  likebinds.value = await findMyLikeBind()
}

const checkLogin = () => {
  if (!store.logined) {
    dialog.login = true
  }
}

const like = () => {
  if (!store.logined) {
    return
  }
  const param = {
    id: record.value?.id
  }
  likeRecord(param)
  message.message = '読書記録にいいねしました。'
  router.push('/top')
}

const unlike = () => {
  const param = {
    id: record.value?.id
  }
  unlikeRecord(param)
  message.message = 'いいねを解除しました。'
  router.push('/top')
}

onMounted(() => {
  searchBook()
  searchRecord()
  searchLikeBinds()
})
</script>

<template>
  <v-container fluid>
    <Login
      :open="dialog.login"
      @close="dialog.login = false; like();"
    />
    <v-row>
      <v-col cols="1"></v-col>
      <v-col cols="3">
        <BookRanking :books="books" />
      </v-col>
      <v-col cols="7">
        <RecordCarousel
          :key="likebinds"
          :records="records" 
          :books="books" 
          :likebinds="likebinds"
          @like="(item: Record) => { record = item; checkLogin(); like(); }"
          @unlike="(item: Record) => { record = item; unlike(); }"
          @reload="searchLikeBinds"
        />
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
  </v-container>
</template>