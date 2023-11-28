<script setup lang="ts">
import { useStore } from '@/stores';
import type { RakutenBook } from '@/types';
import BookSearch from '@/ui/components/modules/visitor/mypage/BookSearch.vue';
import RecordRegister from '@/ui/components/modules/visitor/mypage/RecordRegister.vue';
import { ref } from 'vue';
import { onBeforeRouteLeave } from 'vue-router';

const store = useStore()

const open = ref(false)
const selected = ref<RakutenBook | undefined>(store.book)

onBeforeRouteLeave(() => {
  store.book = undefined
})

</script>

<template>
  <v-container>
    <BookSearch 
      :open="open"
      @select="(item: RakutenBook) => {selected = item; open = false;}"
      @close="open = false"
    />
    <RecordRegister
      :book="selected"
      @open="open = true"
    />
  </v-container>
</template>