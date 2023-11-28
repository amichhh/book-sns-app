<script setup lang="ts">
import type { Book, Record } from '@/types';
import { mdiHeart } from '@mdi/js';

const props = defineProps<{ records: Record[], books: Book[] }>()

const getBook = (id: number) => {
  return props.books.find(v => v.id === id)
}
</script>

<template>
  <v-sheet class="pa-10" height="100%">
    <v-row dense>
      <v-col class="title">読書記録を見る</v-col>
    </v-row>
    <v-row dense>
      <v-col class="text-caption">あなたが本を読んだときに何を感じたのか振り返ってみましょう。</v-col>
    </v-row>
    <v-row v-for="item in props.records" :key="item.id">
      <v-col>
        <v-card variant="flat" color="#f8f8f8" class="py-2">
          <v-card-text>
            <v-row class="fill-height justify-center">
              <v-col cols="2">
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
                  <v-col>
                    {{ item.registerDate }}
                  </v-col>
                  <v-col class="text-right">
                    <v-icon :icon="mdiHeart"></v-icon>
                    {{ item.likeCount }}
                    <v-chip size="small" :color="item.isPublic ? 'teal' : ''" class="mx-2">
                      {{ item.isPublic ? '公開' : '非公開' }}
                    </v-chip>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="impression">
                    {{ item.impression }}
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped>
.title {
  font-size: 20px;
  font-weight: 900;  
}
.book-title {
  font-size: 15px;
  font-weight: 800;
}
.name {
  font-weight: 800;
}
.impression {
  font-size: 14px;
  background-color: #f8f8f8;
  border-radius: 15px;
}
</style>