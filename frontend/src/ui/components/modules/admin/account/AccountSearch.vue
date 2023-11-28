<script setup lang="ts">
import type { Account } from '@/types';
import { mdiMagnify } from '@mdi/js';
import { ref } from 'vue';

const props = defineProps<{ accounts: Account[] }>()
const emit = defineEmits(['filter'])

const keyword = ref<string>("")

const filterAccounts = () => {
  const regex = new RegExp(`.*${keyword.value}.*`)
  const filtered = keyword.value ? props.accounts.filter(v => regex.test(v.accountId) ||  regex.test(v.userName)) : props.accounts
  emit('filter', filtered) 
}
</script>

<template>
  <v-card variant="flat" class="pa-2" color="transparent">
    <v-row dense>
      <v-col cols="10">
        <v-text-field
          v-model="keyword"
          :prepend-inner-icon="mdiMagnify"
          placeholder="アカウントIDまたはユーザー名で検索"
          hide-details
          variant="outlined"
          density="compact"
          clearable
        ></v-text-field>
      </v-col>
      <v-col cols="2">
        <v-btn variant="flat" color="teal" class="ma-1" @click="filterAccounts">検索</v-btn>
      </v-col>
    </v-row>
  </v-card>
</template>