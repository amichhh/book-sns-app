<script setup lang="ts">
import type { Book } from '@/types';
import BookDeleteConfirm from '@/ui/components/modules/admin/book/BookDeleteConfirm.vue';
import BookEdit from '@/ui/components/modules/admin/book/BookEdit.vue';
import { mdiCloseCircleOutline } from '@mdi/js';
import { computed } from 'vue';

const props = defineProps<{ open: boolean, book: Book | undefined }>()

const open = computed(() => props.open)
</script>

<template>
  <v-dialog v-model="open" width="50vw">
    <v-card>
      <v-card-title>
        <v-row class="justify-end">
          <BookEdit v-if="props.book"
            :book="props.book"
          />
          <BookDeleteConfirm v-if="props.book"
            :id="props.book.id"
          />
          <v-btn
            class="my-2 mr-2"
            variant="plain"
            :icon="mdiCloseCircleOutline"
            @click="() => $emit('close')"
          ></v-btn>
        </v-row>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col>
            <v-img class="mx-auto" width="9vw" :src="props.book?.imagePath"></v-img>  
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-center title">{{ props.book?.title }}</v-col>
        </v-row>
        <v-row dense>
          <v-col class="text-center author">{{ props.book?.author }}</v-col>
        </v-row>
        <v-row dense>
          <v-col class="text-center publisher">{{ props.book?.publisher }}</v-col>
        </v-row>
        <v-row class="text-center text-caption">
          <v-col>{{ props.book?.outline }}</v-col>
        </v-row>
        <v-row class="text-center text-caption">
          <v-col>ISBN : {{ props.book?.isbn }}</v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-row class="text-center my-3">
          <v-col>
            <v-btn variant="outlined" color="teal" :href="props.book?.productPath">商品ページへ</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<style scoped>
.title {
  font-size: 20px;
  font-weight: 800;
}
.author {
  font-size: 15px;
}
.publisher {
  font-size: 12px;
}
</style>