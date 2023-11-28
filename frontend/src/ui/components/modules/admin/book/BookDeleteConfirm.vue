<script setup lang="ts">
import { deleteBook } from '@/api/admin/bookAdmin';
import { mdiDeleteOutline } from '@mdi/js';
import { ref } from 'vue';

const props = defineProps<{ id: number }>()
const open = ref<boolean>(false)

const executeDelete = () => {
  const param = {
    id: props.id
  }
  deleteBook(param)
  open.value = false
}

</script>

<template>
  <v-dialog v-model="open" width="30vw">
    <v-card>
      <v-card-text>
        削除してもよろしいですか？
      </v-card-text>
      <v-card-actions>
        <v-row dense class="text-right">
          <v-col>
            <v-btn variant="text" @click="open = false">キャンセル</v-btn>
            <v-btn variant="flat" color="error darken-1" @click="executeDelete()">OK</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn
    class="my-2"
    variant="plain"
    :icon="mdiDeleteOutline"
    @click="open = true"
  ></v-btn>
</template>