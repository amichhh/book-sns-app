<script setup lang="ts">
import { findRakutenBooks } from '@/api/visitor/book';
import type { RakutenBook, RakutenBooksInputParameter } from '@/types';
import { mdiChevronDown, mdiChevronUp, mdiMagnify } from '@mdi/js';
import { ref, watch } from 'vue';

const tab = ref<string>("")
watch(tab, () => {
  title.value = ""
  author.value = ""
  publisherName.value = ""
})

const title = ref<string>("")
const author = ref<string>("")
const publisherName = ref<string>("")

const detail = ref(false)

const books = ref<RakutenBook[]>([])

const searchBook = async (title: string, author: string, publisherName: string) => {
  const param: RakutenBooksInputParameter = {
    applicationId: import.meta.env.VITE_RAKUTENBOOKS_APPLICATION_ID,
    affiliateId: null,
    format: null,
    callback: null,
    elements: null,
    formatVersion: null,
    title: title === "" ? null : title,
    author: author === "" ? null : author,
    publisherName: publisherName === "" ? null : publisherName,
    size: null,
    isbn: null,
    booksGenreId: null,
    hits: null,
    page: null,
    availability: null,
    outOfStockFlag: null,
    chirayomiFlag: null,
    sort: null,
    limitedFlag: null,
    carrier: null,
    genreInformationFlag: null
  }
  books.value = (await findRakutenBooks(param)).Items.map(v => v.Item)
}
</script>

<template>
  <v-container class="pt-2 pr-0">
    <v-row class="mb-1">
      <v-col class="title">
        <v-icon size="large" :icon="mdiMagnify" class="ml-1 mb-1"></v-icon>
        書籍検索
      </v-col>
    </v-row>
    <v-sheet class="pa-5" height="100%">
      <v-tabs
        v-model="tab"
        color="teal"
        class="mb-3"
      >
        <v-tab value="title">タイトル検索</v-tab>
        <v-tab value="author">著者名検索</v-tab>
        <v-tab value="publisher">出版社名検索</v-tab>
      </v-tabs>
      <v-window v-model="tab">
        <v-window-item value="title">
          <v-row dense>
            <v-col>
              <v-text-field
                v-model="title"
                variant="outlined"
                density="compact"
                :prepend-inner-icon="mdiMagnify"
                placeholder="書籍タイトルで検索"
                hide-details 
              ></v-text-field>
            </v-col>
          </v-row>
        </v-window-item>
        <v-window-item value="author">
          <v-row dense>
            <v-col>
              <v-text-field
                v-model="author"
                variant="outlined"
                density="compact"
                :prepend-inner-icon="mdiMagnify"
                placeholder="著者名で検索"
                hide-details 
              ></v-text-field>
            </v-col>
          </v-row>
        </v-window-item>
        <v-window-item value="publisher">
          <v-row dense>
            <v-col>
              <v-text-field
                v-model="publisherName"
                variant="outlined"
                density="compact"
                :prepend-inner-icon="mdiMagnify"
                placeholder="出版社名で検索"
                hide-details 
              ></v-text-field>
            </v-col>
          </v-row>
        </v-window-item>
      </v-window>
      <v-row dense>
        <v-col>
          <v-card variant="flat" color="#e8e8e8" :height="detail ? '20vh' : '5vh'" class="mt-3 mb-1">
            <v-btn   
              variant="plain"
              :ripple="false"
              :append-icon="!detail ? mdiChevronDown : mdiChevronUp"
              @click="detail = !detail"
            >
              詳細検索
            </v-btn>
            <v-card-text>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <v-row dense>
        <v-col></v-col>
        <v-col class="text-center">
          <v-btn block variant="flat" color="teal" @click="searchBook(title, author, publisherName)">検索</v-btn>
        </v-col>
        <v-col></v-col>
      </v-row>
      <v-row>
        <v-col class="text-caption">
          検索結果
          <span>{{ books.length }}件</span>
        </v-col>
      </v-row>
      <v-row
        v-for="item in books"
        :key="item.isbn"
      >
        <v-card variant="flat" class="ma-1" width="100%" color="#f8f8f8">
          <v-card-text>
            <v-row>
              <v-col cols="2">
                <v-img cover width="6vw" :src="item.largeImageUrl"></v-img>
              </v-col>
              <v-col>
                <v-row dense>
                  <v-col class="book-title">{{ item.title }}</v-col>
                </v-row>
                <v-row dense>
                  <v-col class="text-caption">{{ item.author }}</v-col>
                </v-row>
                <v-row dense>
                  <v-col class="text-caption pt-0">{{ item.publisherName }}</v-col>
                </v-row>
                <v-row dense>
                  <v-col>
                    <v-btn
                      variant="flat"
                      color="teal"
                      class="mt-1"
                      @click="() => $emit('write', item)"
                    >
                      読書記録を書く
                    </v-btn>
                  </v-col>
                </v-row>
              </v-col>
            </v-row>
          </v-card-text>
        </v-card>
      </v-row>
    </v-sheet>
  </v-container>
</template>

<style scoped>
.title {
  font-size: 22px;
  font-weight: 900;
}
.book-title {
  font-size: 15px;
  font-weight: 800;
}
</style>