<script setup lang="ts">
import { findAccount } from '@/api/admin/accountAdmin';
import type { Account } from '@/types';
import AccountRegister from '@/ui/components/modules/admin/account/AccountRegister.vue';
import AccountSearch from '@/ui/components/modules/admin/account/AccountSearch.vue';
import AccountTable from '@/ui/components/modules/admin/account/AccountTable.vue';
import { onMounted, ref } from 'vue';

const accounts = ref<Account[]>([])
const filteredAccounts = ref<Account[]>([])

const searchAccount = async () => {
  accounts.value = await findAccount()
  filteredAccounts.value = await findAccount()
}

onMounted(() => {
  searchAccount()
})
</script>

<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="8">
        <AccountSearch
          :accounts="accounts"
          @filter="(filtered: Account[]) => { filteredAccounts = filtered }"
        />
      </v-col>
      <v-col class="text-right mt-1">
        <AccountRegister
          @reload="searchAccount"
        />
      </v-col>
    </v-row>
    <v-row dense>
      <v-col>
        <AccountTable
          :accounts="filteredAccounts"
        />
      </v-col>
    </v-row>
  </v-container>
</template>