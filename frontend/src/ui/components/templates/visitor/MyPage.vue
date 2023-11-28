<script setup lang="ts">
import Header from '@/ui/components/modules/visitor/mypage/Header.vue';
</script>

<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="1"></v-col>
      <v-col cols="10">
        <v-row dense>
          <v-col>
            <Header />
          </v-col>
        </v-row>
        <v-row dense>
          <v-col>
            <RouterView />
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="1"></v-col>
    </v-row>
  </v-container>
</template>