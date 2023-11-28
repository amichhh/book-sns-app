<script setup lang="ts">
import type { Book, Record } from '@/types';
import { mdiHeart, mdiStar } from '@mdi/js';

const props = defineProps<{ records: Record[], books: Book[] }>()

const convertBookTitle = (bookId: number) => {
  return props.books.find(v => v.id === bookId)?.title
}
</script>

<template>
  <v-table fixed-header density="compact">
    <thead>
      <tr>
        <th class="text-left text-caption">作成者ID</th>
        <th class="text-left text-caption">書籍タイトル</th>
        <th class="text-center text-caption">評価</th>
        <th class="text-left text-caption">感想</th>
        <th class="text-center text-caption">いいね数</th>
        <th class="text-center text-caption">公開設定</th>
        <th class="text-center text-caption">最終更新日</th>
      </tr>
    </thead>
    <tbody>
      <tr v-if="props.records.length === 0">
        <td colspan="6">
          <v-alert type="warning" color="teal-lighten-2" class="ma-5" density="compact">
            読書記録が見つかりませんでした
          </v-alert>
        </td>
      </tr>
      <tr 
        v-for="item in props.records"
        :key="item.id"
        class="selectable"
        @click="() => $emit('select', item)"
      >
        <td>{{ item.accountId }}</td>
        <td>{{ convertBookTitle(item.bookId) }}</td>
        <td class="text-center">
          <v-icon size="small" color="teal" class="mb-1" :icon="mdiStar"></v-icon>
          {{ item.evaluation }}
        </td>
        <td class="text-truncate" style="max-width: 30vw;">{{ item.impression }}</td>
        <td class="text-center">
          <v-icon size="small" color="teal" class="mb-1" :icon="mdiHeart"></v-icon>
          {{ item.likeCount }}
        </td>
        <td class="text-center">
          <v-chip size="small" :color="item.isPublic ? 'teal' : 'deep-orange'">
            {{ item.isPublic ? "公開" : "非公開" }}
          </v-chip>
        </td>
        <td class="text-center">{{ item.updateDate }}</td>
      </tr>
    </tbody>
  </v-table>
</template>