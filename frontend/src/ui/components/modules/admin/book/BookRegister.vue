<script setup lang="ts">
import { registerBook } from '@/api/visitor/book';
import { bookGenre } from '@/types';
import { mdiCloseCircleOutline } from '@mdi/js';
import { ref } from 'vue';

const open = ref<boolean>(false)

const title = ref<string>()
const author = ref<string>()
const outline = ref<string>()
const genre = ref<string[]>()

const emit = defineEmits(['reload']);

const genres = () => {
  return Object.entries(bookGenre).map(([key, value]) => (
    {title: value, value: key}
  ))
}

const register = () => {
  const param = {
    title: title.value,
    author: author.value,
    outline: outline.value,
    genre: genre.value
  }
  registerBook(param)
  open.value = false
  emit('reload')
}

</script>

<template>
  <v-dialog v-model="open" width="50vw">
    <v-card class="pa-5">
      <v-card-title class="text-center">
        <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">書籍登録</v-col>
          <v-col class="text-right">
            <v-btn
              variant="plain"
              :icon="mdiCloseCircleOutline"
              @click="open = false"
            ></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text class="px-10">
        <v-text-field 
          v-model="title"
          label="タイトル"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-text-field>
        <v-text-field
          v-model="author"
          label="著者"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-text-field>
        <v-textarea
          v-model="outline"
          label="概要"
          rows="8"
          counter="400"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-textarea>
        <v-select
          v-model="genre"
          :items="genres()"
          label="ジャンル"
          chips
          multiple
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-select>
      </v-card-text>
      <v-card-actions>
        <v-row dense>
          <v-col class="text-center">
            <v-btn variant="flat" color="teal" @click="register">登録</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn variant="flat" color="teal" class="ma-2" @click="open = true">新規登録</v-btn>
</template>

<style scoped>
.title {
  font-weight: 900;
}
</style>