<script setup lang="ts">
import type { RakutenBook } from '@/types';
import BookSearch from '@/ui/components/modules/visitor/book/BookSearch.vue';
import { mdiCloseCircleOutline } from '@mdi/js';
import { computed } from 'vue';

const props = defineProps<{ open: boolean }>()

const open = computed(() => props.open)
</script>

<template>
  <v-dialog v-model="open" width="80vw">
    <v-card>
      <v-card-text>
        <v-row dense>
          <v-col class="text-right pb-0">
            <v-btn
              variant="plain"
              :icon="mdiCloseCircleOutline"
              @click="() => $emit('close')"
              density="compact"
            ></v-btn>
          </v-col>
        </v-row>
        <v-row dense>
          <v-col class="pt-0">
            <BookSearch
              class="mt-0"
              @write="(item: RakutenBook) => $emit('select', item)"
            />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>
