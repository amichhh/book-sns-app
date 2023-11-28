<script setup lang="ts">
import { editAccount } from '@/api/visitor/account';
import { useStore } from '@/stores';
import type { Account } from '@/types';
import { mdiCloseCircleOutline } from '@mdi/js';
import { ref } from 'vue';

const store = useStore()
const open = ref<boolean>(false)

const userName = ref<string>(store.user.userName)
const email = ref<string>(store.user.email)
const password = ref<string>("")
const passwordConfirm = ref<string>("")

const edit = async () => {
  if (password.value !== passwordConfirm.value) {
    console.log("パスワードが一致していません。")
    return
  }
  const param = {
    accountId: store.user.accountId,
    userName: userName.value,
    rawPassword: password.value,
    email: email.value
  }
  const account: Account = await editAccount(param)
  store.user.userName = account.userName
  store.user.userName = account.email
  open.value = false
}
</script>

<template>
  <v-dialog v-model="open" width="40vw">
    <v-card class="pa-5">
      <v-card-title class="text-center">
        <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">アカウント情報修正</v-col>
          <v-col class="text-right">
            <v-btn variant="plain" :icon="mdiCloseCircleOutline" @click="open = false"></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text class="px-10">
        <v-row dense>
          <v-col class="text-caption">
            ログインID
          </v-col>
        </v-row>
        <v-row dense>
          <v-col class="text-subtitle-1">
            {{ store.user.accountId }}
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field
              v-model="userName"
              label="ユーザー名"
              variant="outlined"
              color="teal"
              density="compact"
              persistent-placeholder 
              counter="60"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <v-text-field 
              v-model="email" 
              label="メールアドレス" 
              type="email" 
              variant="outlined" 
              color="teal" 
              density="compact"
              persistent-placeholder 
              counter="256"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <v-text-field 
              v-model="password" 
              label="パスワード" 
              type="password" 
              variant="outlined" 
              color="teal"
              density="compact" 
              persistent-placeholder 
              counter="64" 
              class="password"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <v-text-field 
              v-model="passwordConfirm" 
              label="パスワード確認" 
              type="password" 
              variant="outlined" 
              color="teal"
              density="compact" 
              persistent-placeholder 
              counter="64" 
              class="password"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-row dense>
          <v-col class="text-center">
            <v-btn variant="flat" color="teal" @click="edit">修正する</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn block variant="outlined" rounded color="teal" @click="open = true">アカウント情報を修正する</v-btn>
</template>

<style scoped>
.title {
  font-weight: 900;
}

.password {
  font-family: sans-serif;
}
</style>