<script setup lang="ts">
import { editBook } from '@/api/visitor/book';
import { bookGenre, type Book } from '@/types';
import { mdiCloseCircleOutline, mdiSquareEditOutline } from '@mdi/js';
import { ref } from 'vue';

const props = defineProps<{ book: Book }>()

const open = ref<boolean>(false)

const title = ref<string>(props.book.title)
const author = ref<string>(props.book.author)
const outline = ref<string>(props.book.outline)
const genre = ref<string[]>(props.book.genre)

const emit = defineEmits(['reload']);

const genres = () => {
  return Object.entries(bookGenre).map(([key, value]) => (
    { title: value, value: key }
  ))
}

const edit = () => {
  const param = {
    id: props.book.id,
    title: title.value,
    author: author.value,
    outline: outline.value,
    genre: genre.value
  }
  editBook(param)
  open.value = false
  emit('reload')
}

</script>

<template>
  <v-dialog v-model="open" width="50vw">
     <v-card class="pa-5">
        <v-card-title class="text-center"> <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">書籍編集</v-col>
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
            <v-btn variant="flat" color="teal" @click="edit">編集</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn
    class="my-2"
    variant="plain"
    :icon="mdiSquareEditOutline"
    @click="open = true"
  ></v-btn>
</template>

<style scoped>
.title {
  font-weight: 900;
}
</style>