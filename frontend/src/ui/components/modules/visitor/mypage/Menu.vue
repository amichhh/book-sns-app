<script setup lang="ts">
import router from '@/router';
import { mdiBookHeart, mdiBookOpenPageVariant, mdiBookmarkMultiple, mdiBookshelf, mdiFountainPenTip } from '@mdi/js';
</script>

<template>
  <v-sheet class="pa-6" height="100%">
    <v-row>
      <v-col>
        <v-btn
          block
          size="large"
          variant="flat"
          height="17vh"
          color="teal"
          @click="router.push('/mypage/record/write')"
        >
          <div>
            <v-icon size="x-large" :icon="mdiFountainPenTip" class="mb-2"></v-icon>
            <br>
            読書記録を書く
          </div>
        </v-btn>
      </v-col>
      <v-col>
        <v-btn
          block
          size="large"
          variant="flat"
          height="17vh"
          color="teal"
          @click="router.push('/mypage/record/list')"
        >
          <div>
            <v-icon size="x-large" :icon="mdiBookOpenPageVariant" class="mb-2"></v-icon>
            <br>
            読書記録を見る
          </div>
        </v-btn>
      </v-col>
      <v-col>
        <v-btn
          block
          size="large"
          variant="flat"
          height="17vh"
          color="teal"
          @click="router.push('/mypage/record/like')"
        >
          <div>
            <v-icon size="x-large" :icon="mdiBookHeart" class="mb-2"></v-icon>
            <br>
            いいねした読書記録を見る
          </div>
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn
          block
          size="large"
          variant="flat"
          height="17vh"
          color="teal"
          @click="router.push('/mypage/book/read')"
        >
          <div>
            <v-icon size="x-large" :icon="mdiBookshelf" class="mb-2"></v-icon>
            <br>
            読んだ本を見る
          </div>
        </v-btn>
      </v-col>
      <v-col>
        <v-btn
          block
          size="large"
          variant="flat"
          height="17vh"
          color="teal"
          @click="router.push('/mypage/book/want')"
        >
          <div>
            <v-icon size="x-large" :icon="mdiBookmarkMultiple" class="mb-2"></v-icon>
            <br>
            読みたい本を見る
          </div>
        </v-btn>
      </v-col>
      <v-col></v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped>
.title {
  font-size: 22px;
  font-weight: 900;
}
</style>