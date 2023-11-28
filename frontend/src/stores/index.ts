import type { Account, RakutenBook } from '@/types'
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useStore = defineStore('store', () => {
  const user = ref<Account>({
    accountId: '',
    userName: '',
    password: '',
    email: '',
    authority: '',
    status: ''
  })
  const logined = ref(false)
  // ログイン処理を挟む画面遷移時に、書籍情報を保持する目的で使用します。
  const book = ref<RakutenBook>()
  return { user, logined, book }
})
