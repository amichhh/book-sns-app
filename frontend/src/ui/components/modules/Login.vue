<script setup lang="ts">
import { login } from '@/api/visitor/auth';
import router from '@/router';
import { useStore } from '@/stores';
import { useMessageStore } from '@/stores/message';
import type { LoginForm } from '@/types';
import AccountRegister from '@/ui/components/modules/visitor/account/AccountRegister.vue';
import { mdiAccountCircle, mdiCloseCircleOutline, mdiLock } from '@mdi/js';
import { computed, ref } from 'vue';

const store = useStore()
const message = useMessageStore()

const props = defineProps<{ open: boolean }>()
const open = computed(() => props.open)

const emit = defineEmits(['close'])

const username = ref<string>("")
const password = ref<string>("")

const doLogin = () => {
  const param: LoginForm = {
    userId: username.value,
    password: password.value
  }
  login(param)
    .then((res) => {
      store.user = res
      store.logined = true
      router.push("/mypage")
      message.setMessage("ログインに成功しました")
      emit('close')
    })
    .catch(() => {
      message.setMessage("IDまたはパスワードが間違っています")
    })
}
</script>

<template>
  <v-dialog v-model="open" width="40vw">
    <v-card  class="px-10 py-5">
      <v-card-title>
        <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">ログイン</v-col>
          <v-col class="text-right">
            <v-btn
              variant="plain"
              :icon="mdiCloseCircleOutline"
              @click="() => $emit('close')"
            ></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text class="px-10">
        <v-row dense>
          <v-col>
            <v-text-field
              v-model="username"
              label="ログインID"
              variant="outlined"
              density="compact"
              :prepend-icon="mdiAccountCircle"
              persistent-placeholder
              class="login"
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
              density="compact"
              :prepend-icon="mdiLock"
              persistent-placeholder
              class="login"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row dense class="text-center">
          <v-col class="mx-10">
            <v-btn block variant="flat" rounded color="teal" @click="doLogin">ログイン</v-btn>
          </v-col>
        </v-row>
        <v-row class="text-center">
            <v-col class="mx-10 text-caption">
              アカウント登録がまだの方はこちら
            </v-col>
          </v-row>
        <v-row dense class="text-center">
          <v-col class="mx-10">
            <AccountRegister />
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<style scoped>
.title {
  font-weight: 900;
}
.login {
  font-family: sans-serif;
}
</style>