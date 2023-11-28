<script setup lang="ts">
import { logout } from '@/api/visitor/auth';
import router from '@/router';
import { useStore } from '@/stores';
import { useMessageStore } from '@/stores/message';

const store = useStore()
const message = useMessageStore()

const doLogout = () => {
  logout()
    .then(() => {
      store.logined = false
        store.user = {
          accountId: "",
          userName: "",
          password: "",
          email: "",
          authority: "",
          status: ""
        }
        message.setMessage("ログアウトしました")
        router.push("/")
    })
    .catch(() => {
      message.setMessage("ログアウトに失敗しました")
    })
  
}
</script>

<template>
  <v-btn variant="outlined" color="teal" @click="doLogout">ログアウト</v-btn>
</template>