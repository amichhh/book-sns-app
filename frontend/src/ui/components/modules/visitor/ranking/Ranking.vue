<script setup lang="ts">
import type { Book } from '@/types';
import { mdiCrown } from '@mdi/js';

const props = defineProps<{ books: Book[] }>()

const colorCrown = (rank: number) => {
  if (rank === 1) {
    return 'lime-darken-2'
  }
  else if (rank === 2) {
    return 'blue-grey-lighten-2'
  }
  else if (rank === 3) {
    return 'brown'
  }
}
</script>

<template>
  <v-container class="pt-2 pr-0">
    <v-row class="mb-1">
      <v-col class="title">
        <v-icon size="large" :icon="mdiCrown" class="ml-1 mb-1"></v-icon>
        全期間 読書ランキング
      </v-col>
    </v-row>
    <v-sheet class="pa-6" height="100%">
      <v-row v-for="(item, index) in props.books" :key="item.id">
        <v-card variant="flat" class="ma-1" width="100%" color="#f8f8f8">
          <v-card-text>
            <v-row>
              <v-col cols="1">
                <v-row dense>
                  <v-col>
                    <v-icon
                      v-if="index < 3"
                      :icon="mdiCrown"
                      :color="colorCrown(index+1)"
                      size="large"
                    ></v-icon>
                  </v-col>
                </v-row>
                <v-row dense>
                  <v-col>{{ index + 1 }}位</v-col>
                </v-row>
              </v-col>
              <v-col cols="3">
                <v-img width="9vw" :src="item.imagePath"></v-img>
              </v-col>
              <v-col cols="8">
                <v-row dense>
                  <v-col class="book-title">{{ item.title }}</v-col>
                </v-row>
                <v-row dense>
                  <v-col class="text-caption">{{ item.author }}</v-col>
                  <v-col class="text-caption">
                    <v-chip color="teal" size="x-small">読書記録</v-chip>
                    {{ item.recordCount }}件
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn variant="outlined" color="teal">読書記録を見る</v-btn>
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
  font-size: 22px;
  font-weight: 900;
}

.book-title {
  font-size: 15px;
  font-weight: 800;
}
</style>