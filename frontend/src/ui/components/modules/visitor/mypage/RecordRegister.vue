<script setup lang="ts">
import { registerBook } from '@/api/visitor/book';
import { registerRecord } from '@/api/visitor/record';
import { useMessageStore } from '@/stores/message';
import type { RakutenBook } from '@/types';
import { ref } from 'vue';

const message = useMessageStore()
const props = defineProps<{ book: RakutenBook | undefined }>()

const evaluation = ref<number>()
const impression = ref<string>()
const isPublic = ref<boolean>(false)

const register = async () => {
  const bookParam = {
    title: props.book?.title,
    author: props.book?.author,
    publisher: props.book?.publisherName,
    outline: props.book?.itemCaption,
    genre: [],
    isbn: props.book?.isbn,
    imagePath: props.book?.largeImageUrl,
    productPath: props.book?.itemUrl
  }
  const bookId = (await registerBook(bookParam)).id
  const recordParam = {
    bookId: bookId,
    evaluation: evaluation.value,
    impression: impression.value,
    isPublic: isPublic.value
  }
  registerRecord(recordParam)
  message.message = '読書記録を作成しました。'
}
</script>

<template>
  <v-sheet class="pa-10" height="100%">
    <v-row dense>
      <v-col class="title">読書記録を書く</v-col>
    </v-row>
    <v-row dense>
      <v-col class="text-caption">あなたが読んだ本について感じたことや思ったことを書き留めておきましょう。</v-col>
    </v-row>
    <v-row>
      <v-col>①読んだ本を選択してください。</v-col>
    </v-row>
    <v-row dense>
      <v-col cols="2">
        <v-btn variant="flat" color="teal" @click="() => $emit('open')">書籍を検索する</v-btn>
      </v-col>
      <v-col v-if="props.book" class="mt-1 text-teal">
        『{{ props.book.title }}』({{ props.book.author }})
      </v-col>
      <v-col v-else class="mt-2 text-subtitle-2">
        選択してください
      </v-col>
    </v-row>
    <v-row>
      <v-col>②感想を書いてください。</v-col>
    </v-row>
    <v-row dense>
      <v-col cols="2">
        <v-rating
          v-model="evaluation"
          color="teal"
          density="compact"
        ></v-rating>
      </v-col>
      <v-col>
        <v-btn
          size="small"
          variant="plain"
          @click="evaluation = undefined"
          class="ml-2"
        >
          評価を記録しない
        </v-btn>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
    <v-row dense>
      <v-col>
        <v-textarea
          v-model="impression"
          label="感想"
          variant="outlined"
          color="teal"
          density="compact"
          rows="8"
          counter="400"
          persistent-placeholder
        ></v-textarea>
      </v-col>
    </v-row>
    <v-row dense>
      <v-col>③公開設定を選択してください。</v-col>
    </v-row>
    <v-row dense>
      <v-col>
        <v-switch 
          v-model="isPublic"
          :label="isPublic ? '公開する' : '公開しない'"
          color="teal"
          hide-details
          inset
          density="compact"
        ></v-switch>
      </v-col>
    </v-row>
    <v-row>
      <v-col class="text-center">
        <v-btn size="large" variant="flat" color="teal" @click="register">保存</v-btn>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped>
.title {
  font-size: 20px;
  font-weight: 900;  
}
</style>