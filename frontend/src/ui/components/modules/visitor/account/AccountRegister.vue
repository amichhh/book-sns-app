<script setup lang="ts">
import { registerAccount } from '@/api/visitor/account';
import { mdiCloseCircleOutline } from '@mdi/js';
import { ref } from 'vue';

const open = ref<boolean>(false)

const accountId = ref<string>()
const userName = ref<string>()
const email = ref<string>()
const password = ref<string>("")
const passwordConfirm = ref<string>("")

const emit = defineEmits(['reload']);

const register = () => {
  if (password.value !== passwordConfirm.value) {
    console.log("パスワードが一致していません。")
    return
  }
  const param = {
    accountId: accountId.value,
    userName: userName.value,
    rawPassword: password.value,
    email: email.value
  }
  registerAccount(param)
    .then(() => {
      open.value = false
      emit('reload')
    })
}
</script>

<template>
  <v-dialog v-model="open" width="40vw">
    <v-card class="pa-5">
      <v-card-title class="text-center"> <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">アカウント登録</v-col>
          <v-col class="text-right">
            <v-btn variant="plain" :icon="mdiCloseCircleOutline" @click="open = false"></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text class="px-10">
        <v-row dense>
          <v-col>
            <v-text-field
              v-model="accountId"
              label="ログインID"
              variant="outlined"
              color="teal"
              density="compact"
              persistent-placeholder
              counter="30"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row dense>
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
            <v-btn variant="flat" color="teal" @click="register">登録</v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn block variant="outlined" rounded color="teal" class="mb-3" @click="open = true">新規登録</v-btn>
</template>

<style scoped>
.title {
  font-weight: 900;
}
.password {
  font-family: sans-serif;
}
</style>