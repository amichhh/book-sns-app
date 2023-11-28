<script setup lang="ts">
import { useStore } from '@/stores';
import AccountEdit from '@/ui/components/modules/visitor/account/AccountEdit.vue';
import { mdiAccountCircle, mdiCloseCircleOutline } from '@mdi/js';
import { ref } from 'vue';

const store = useStore()

const open = ref<boolean>(false)
</script>

<template>
  <v-dialog v-model="open" width="35vw">
    <v-card class="pa-5">
      <v-card-title class="text-center">
        <v-row>
          <v-col></v-col>
          <v-col class="title text-center mt-3">アカウント情報</v-col>
          <v-col class="text-right">
            <v-btn
              variant="plain"
              :icon="mdiCloseCircleOutline"
              @click="open = false"
            ></v-btn>
          </v-col>
        </v-row>
      </v-card-title>
      <v-card-text>
        <v-row dense>
          <v-col cols="5">
            <v-card variant="flat" color="teal" class="text-center text-caption pa-1" density="compact">ログインID</v-card>
          </v-col>
          <v-col>{{ store.user.accountId }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="5">
            <v-card variant="flat" color="teal" class="text-center text-caption pa-1" density="compact">ユーザー名</v-card>
          </v-col>
          <v-col>{{ store.user.userName }}</v-col>
        </v-row>
        <v-row dense>
          <v-col cols="5">
            <v-card variant="flat" color="teal" class="text-center text-caption pa-1" density="compact">メールアドレス</v-card>
          </v-col>
          <v-col>{{ store.user.email }}</v-col>
        </v-row>
      </v-card-text>
      <v-card-actions>
        <v-row>
          <v-spacer></v-spacer>
          <v-col cols="8">
            <AccountEdit />
          </v-col>
          <v-spacer></v-spacer>
        </v-row>
      </v-card-actions>
    </v-card>
  </v-dialog>
  <v-btn :icon="mdiAccountCircle" @click="open = true"></v-btn>
</template>

<style scoped>
.title {
  font-weight: 900;
}
</style>