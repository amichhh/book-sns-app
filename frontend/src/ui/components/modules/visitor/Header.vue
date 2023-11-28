<script setup lang="ts">
import { getLoginInformation } from '@/api/visitor/auth';
import { useStore } from '@/stores';
import Login from '@/ui/components/modules/Login.vue';
import Logout from '@/ui/components/modules/Logout.vue';
import AccountInfo from '@/ui/components/modules/visitor/account/AccountInfo.vue';
import { onMounted, reactive } from 'vue';

const store = useStore()

const getLoginInfo = async () => {
  const loginInfo = await getLoginInformation()
  store.logined = loginInfo.logined
  store.user = loginInfo.loginUser
}

const dialog = reactive({
  login: false,
  admin: false
})

onMounted(() => {
  getLoginInfo()
})
</script>

<template>
  <v-app-bar elevation="0" color="white" :height="150" scroll-behavior="inverted">
    <img src="@/assets/logo.png" class="logo"/>
    <div>
      <div class="title">share book!</div>
      <div class="subtitle">読んだら、感想をシェアしよう。</div>
    </div>
    <v-spacer></v-spacer>
    <div class="menu">
      <v-list nav color="teal">
        <v-list-item class="px-5" title="トップ" to="/top"></v-list-item>
        <v-divider color="teal" vertical inset></v-divider>
        <v-list-item class="px-5" title="書籍を探す" to="/book"></v-list-item>
        <v-divider color="teal" vertical inset></v-divider>
        <v-list-item class="px-5" title="読書ランキング" to="/ranking"></v-list-item>
        <v-divider color="teal" vertical inset></v-divider>
        <v-list-item class="px-5" title="このサイトについて" to="/about"></v-list-item>
        <v-divider v-if="store.logined" color="teal" vertical inset></v-divider>
        <v-list-item v-if="store.logined" class="px-5" title="マイページ" to="/mypage"></v-list-item>
        <v-divider v-if="store.logined && store.user.authority === 'ROLE_ADMIN'" color="teal" vertical inset></v-divider>
        <v-list-item v-if="store.logined && store.user.authority === 'ROLE_ADMIN'" class="px-5" title="管理者ページ" @click="dialog.admin = true">
          <v-dialog v-model="dialog.admin" width="35vw">
            <v-card>
              <v-card-text>
                管理者ページに遷移します。よろしいですか？
              </v-card-text>
              <v-card-actions>
                <v-row dense class="text-right">
                  <v-col>
                    <v-btn @click="dialog.admin = false">キャンセル</v-btn>
                    <v-btn variant="flat" color="teal" to="/admin">OK</v-btn>
                  </v-col>
                </v-row>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-list-item>
      </v-list>   
    </div>
    <v-spacer></v-spacer>
    <div v-if="!store.logined" class="mr-10">
      <Login 
        :open="dialog.login"
        @close="dialog.login = false"
      />
      <v-btn variant="flat" color="teal" @click="dialog.login = true">ログイン</v-btn>
    </div>
    <div v-if="store.logined" class="mr-10">
      <v-row dense>
        <v-col class="text-center">
          <AccountInfo />
          {{ store.user.userName }}
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <Logout />
        </v-col>
      </v-row>
    </div>
  </v-app-bar>
</template>

<style scoped>
.menu {
  margin-top: 85px;
}

.v-list {
  display: flex;
}

.logo {
  margin-left: 80px;
  height: 80px;
  width: auto;
}

.title {
  font-size: 32px;
  font-weight: 800;
  color: rgb(11, 182, 182);
}

.subtitle {
  padding-left: 24px;
  font-size: 10px;
  color: rgb(5, 98, 98);
}
</style>