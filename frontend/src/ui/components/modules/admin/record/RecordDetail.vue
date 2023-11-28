<script setup lang="ts">
import type { Record } from '@/types';
import { mdiCloseCircleOutline } from '@mdi/js';
import { computed } from 'vue';

const props = defineProps<{ open: boolean, record: Record | undefined }>()

const open = computed(() => props.open)
</script>

<template>
  <v-dialog v-model="open" width="60vw">
    <v-card class="px-7">
      <v-card-title class="text-center">
        <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">読書記録詳細</v-col>
          <v-col class="text-right">
            <v-btn
              variant="plain"
              :icon="mdiCloseCircleOutline"
              @click="() => $emit('close')"
            ></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text>
        <v-row dense>
          <v-col cols="2" class="text-caption">読書記録ID</v-col>
          <v-col>{{ props.record?.id }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">作成者ID</v-col>
          <v-col> {{ props.record?.accountId }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">書籍ID</v-col>
          <v-col> {{ props.record?.bookId }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">評価</v-col>
          <v-col> {{ props.record?.evaluation }} </v-col>
        </v-row>
        <v-row dense>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">感想</v-col>
          <v-col>{{ props.record?.impression }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">いいね数</v-col>
          <v-col>{{ props.record?.likeCount }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">登録日</v-col>
          <v-col>{{ props.record?.registerDate }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="2" class="text-caption">最終更新日</v-col>
          <v-col>{{ props.record?.updateDate }}</v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-row class="justify-center my-3">
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>
.title {
  font-weight: 900;
}
</style>