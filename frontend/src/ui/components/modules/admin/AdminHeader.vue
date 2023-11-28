<script setup lang="ts">
import { mdiAccountOutline, mdiBookOpenVariant, mdiBookmarkBoxMultipleOutline } from '@mdi/js';
</script>

<template>
  <v-app-bar elevation="0" color="teal" :height="55">
    <v-row>
      <v-col class="ml-5">share book! 管理者ページ</v-col>
    </v-row>
  </v-app-bar>
  <v-navigation-drawer
    class="bg-teal-lighten-2"
    theme="dark"
    permanent
    :width="220"
  >
    <v-list nav>
      <v-list-item :prepend-icon="mdiBookOpenVariant" to="/admin/book" class="text-subtitle-2">書籍</v-list-item>
      <v-list-item :prepend-icon="mdiBookmarkBoxMultipleOutline" to="/admin/record" class="text-subtitle-2">読書記録</v-list-item>
      <v-list-item :prepend-icon="mdiAccountOutline" to="/admin/account" class="text-subtitle-2">アカウント</v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>