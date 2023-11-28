<script setup lang="ts">
import type { Account } from '@/types';
import { authorityType, statusType } from '@/types';

const props = defineProps<{ accounts: Account[] }>()

const convertAuthority = (authority: string) => {
  return Object.entries(authorityType).find(([key]) => key === authority)?.[1]
}

const convertStatus = (status: string) => {
  return Object.entries(statusType).find(([key]) => key === status)?.[1]
}
</script>

<template>
  <v-table fixed-header density="compact">
    <thead>
      <tr>
        <th class="text-left text-caption">アカウントID</th>
        <th class="text-left text-caption">ユーザー名</th>
        <th class="text-left text-caption">メールアドレス</th>
        <th class="text-center text-caption">権限</th>
        <th class="text-center text-caption">状態</th>
      </tr>
    </thead>
    <tbody>
      <tr v-if="props.accounts.length === 0">
        <td colspan="5">
          <v-alert type="warning" color="teal-lighten-2" class="ma-5" density="compact">
            アカウントが見つかりませんでした
          </v-alert>
        </td>
      </tr>
      <tr v-for="item in props.accounts" :key="item.accountId" class="selectable">
        <td>{{ item.accountId }}</td>
        <td>{{ item.userName }}</td>
        <td>{{ item.email }}</td>
        <td class="text-center">{{ convertAuthority(item.authority) }}</td>
        <td class="text-center">
          <v-chip size="small" :color="item.status === 'VALID' ? 'teal' : 'grey'">
            {{ convertStatus(item.status) }}            
          </v-chip>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>