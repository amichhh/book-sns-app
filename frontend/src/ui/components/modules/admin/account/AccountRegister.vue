<script setup lang="ts">
import { registerAccount } from '@/api/admin/accountAdmin';
import { authorityType } from '@/types';
import { mdiCloseCircleOutline } from '@mdi/js';
import { ref } from 'vue';

const open = ref<boolean>(false)

const accountId = ref<string>()
const userName = ref<string>()
const password = ref<string>()
const email = ref<string>()
const authority = ref<string>()

const emit = defineEmits(['reload']);

const authorities = () => {
  return Object.entries(authorityType).map(([key, value]) => (
    { title: value, value: key }
  ))
}

const register = () => {
  const param = {
    accountId: accountId.value,
    userName: userName.value,
    rawPassword: password.value,
    email: email.value,
    authority: authority.value
  }
  registerAccount(param)
  open.value = false
  emit('reload')
}
</script>

<template>
  <v-dialog v-model="open" width="40vw">
    <v-card class="pa-5">
      <v-card-title class="text-center"> <v-row>
        <v-col></v-col>
          <v-col class="title text-center mt-3">アカウント登録</v-col>
          <v-col class="text-right">
            <v-btn
              variant="plain"
              :icon="mdiCloseCircleOutline"
              @click="open = false"
            ></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text class="px-10">
        <v-text-field 
          v-model="accountId"
          label="アカウントID"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-text-field>
        <v-text-field
          v-model="userName"
          label="ユーザー名"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-text-field>
        <v-text-field
          v-model="password"
          label="パスワード"
          type="password"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-text-field>
        <v-text-field
          v-model="email"
          label="メールアドレス"
          type="email"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-text-field>
        <v-select
          v-model="authority"
          :items="authorities()" 
          label="権限"
          variant="outlined"
          color="teal"
          density="compact"
          persistent-placeholder
        ></v-select>
      </v-card-text>
      <v-card-actions>
        <v-row dense>
          <v-col class="text-center">
            <v-btn variant="flat" color="teal" @click="register">登録</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn variant="flat" color="teal" class="ma-2" @click="open = true">新規登録</v-btn>
</template>

<style scoped>
.title {
  font-weight: 900;
}
</style>