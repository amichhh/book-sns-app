<script setup lang="ts">
import type { Book } from '@/types';
import { bookGenre } from '@/types';

const props = defineProps<{ books: Book[] }>()

const convertGenre = (genre: string) => {
  return Object.entries(bookGenre).find(([key]) => key === genre)?.[1]
}
</script>

<template>
  <v-table fixed-header density="compact">
    <thead>
      <tr>
        <th class="text-left text-caption">タイトル</th>
        <th class="text-left text-caption">著者</th>
        <th class="text-left text-caption">出版社</th>
        <th class="text-left text-caption">概要</th>
        <th class="text-left text-caption">ジャンル</th>
      </tr>
    </thead>
    <tbody>
      <tr v-if="props.books.length === 0">
        <td colspan="4">
          <v-alert type="warning" color="teal-lighten-2" class="ma-5" density="compact">
            書籍が見つかりませんでした
          </v-alert>
        </td>
      </tr>
      <tr 
        v-for="item in props.books"
        :key="item.id"
        class="selectable"
        @click="() => $emit('select', item)"
      >
        <td>{{ item.title }}</td>
        <td>{{ item.author }}</td>
        <td>{{ item.publisher }}</td>
        <td class="text-truncate" style="max-width: 20vw;">{{ item.outline }}</td>
        <td class="text-center">
          <v-chip-group selected-class="none">
            <v-chip v-for="genre in item.genre" :key="genre" size="x-small">
              {{ convertGenre(genre) }}
            </v-chip>
          </v-chip-group>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>