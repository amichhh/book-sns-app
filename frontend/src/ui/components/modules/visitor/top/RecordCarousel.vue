<script setup lang="ts">
import type { Book, LikeBind, Record } from '@/types';
import { mdiAccountCircle, mdiBookOpenPageVariantOutline, mdiHeart, mdiHeartOutline } from '@mdi/js';
import { computed } from 'vue';

const props = defineProps<{ records: Record[], books: Book[], likebinds: LikeBind[] }>()
const emit = defineEmits(['like', 'unlike'])

const likebinds = computed(() => props.likebinds)

const getBook = (id: number) => {
  return props.books.find(v => v.id === id)
}

const like = (record: Record) => {
  emit('like', record)
}
const unlike = (record: Record) => {
  emit('unlike', record)
}
</script>

<template>
  <v-container class="pt-2 pl-0">
    <v-sheet class="pa-3">
      <v-row class="title">
        <v-col>
          <v-icon :icon="mdiBookOpenPageVariantOutline" class="ml-1 mb-1"></v-icon>
          みんなの読書記録
        </v-col>
      </v-row>
    </v-sheet>
    <v-row v-if="props.records.length === 0">
      <v-col>
        <v-alert color="teal-lighten-2" type="warning" class="ma-5">公開されている読書記録はありません。</v-alert>
      </v-col>
    </v-row>
    <v-carousel v-else cycle height="45vh" hide-delimiters show-arrows="hover">
      <v-carousel-item v-for="item in props.records" :key="item.id">
        <v-sheet class="pa-9" height="100%">
          <v-row class="fill-height justify-center">
            <v-col cols="3">
              <v-row dense>
                <v-col>
                  <v-img width="9vw" :src="getBook(item.bookId)?.imagePath"></v-img>
                </v-col>
              </v-row>
              <v-row dense>
                <v-col class="book-title">
                  {{ getBook(item.bookId)?.title }}
                </v-col>
              </v-row>
              <v-row dense>
                <v-col class="text-caption">
                  {{ getBook(item.bookId)?.author }}
                </v-col>
              </v-row>
            </v-col>
            <v-col cols="9">
              <v-row dense>
                <v-col class="text-h6">
                  <v-icon :icon="mdiAccountCircle" class="mr-2"></v-icon>
                  <span class="name">{{ item.accountId }}</span> さん
                </v-col>
                <v-col class="text-right">
                  <v-btn
                    v-if="!props.likebinds.map(v => v.recordId).includes(item.id)"
                    density="compact"
                    :ripple="false"
                    variant="text"
                    color="teal"
                    :icon="mdiHeartOutline" 
                    @click="like(item)"
                  ></v-btn>
                  <v-btn
                    v-if="props.likebinds.map(v => v.recordId).includes(item.id)"
                    density="compact"
                    :ripple="false"
                    variant="text"
                    color="teal"
                    :icon="mdiHeart" 
                    @click="unlike(item)"
                  ></v-btn>
                  <span>{{ item.likeCount }}</span>
                </v-col>
              </v-row>
              <v-row>
                <v-col class="impression">
                  {{ item.impression }}
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<style scoped>
.title {
  color: white;
  background-color: teal;
}

.book-title {
  font-size: 15px;
  font-weight: 800;
}

.name {
  font-weight: 800;
}

.impression {
  padding: 15px;
  font-size: 14px;
  background-color: #f8f8f8;
  border-radius: 15px;
}
</style>