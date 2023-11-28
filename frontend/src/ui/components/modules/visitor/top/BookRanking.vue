<script setup lang="ts">
import type { Book } from '@/types';
import { mdiCrownOutline } from '@mdi/js';

const props = defineProps<{ books: Book[] }>()
</script>

<template>
  <v-container class="pt-2 pr-0">
    <v-sheet class="pa-3">
      <v-row class="title">
        <v-col>
          <v-icon :icon="mdiCrownOutline" class="ml-1 mb-1"></v-icon>
          読書ランキング
        </v-col>
      </v-row>
    </v-sheet>
    <v-sheet class="pa-5" height="100%">
      <v-row 
        v-for="(item, index) in props.books.slice(0, 5)"
        :key="item.id"
      >
        <v-card variant="flat" class="ma-1" width="100%" color="#f8f8f8">
          <v-card-text>
            <v-row>
              <v-col cols="2">{{ index+1 }}位</v-col>
              <v-col cols="3">
                <v-img width="4vw" :src="item.imagePath"></v-img>
              </v-col>
              <v-col cols="7">
                <v-row dense>
                  <v-col class="book-title">{{ item.title }}</v-col>
                </v-row>
                <v-row dense>
                  <v-col class="text-caption">{{ item.author }}</v-col>                 
                </v-row>
                <v-row dense>
                  <v-col class="text-caption">
                    <v-chip color="teal" size="x-small">読書記録</v-chip>
                    {{ item.recordCount }}件
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-row>
    </v-sheet>
  </v-container>
</template>

<style scoped>
.title {
  color: white;
  background-color: teal;
}

.book-title {
  font-size: 14px;
  font-weight: 600;
}
</style>