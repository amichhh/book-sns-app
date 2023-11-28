import { defineStore } from 'pinia'

export const useMessageStore = defineStore('message', {
  state: () => ({
    message: ''
  }),
  actions: {
    setMessage(text: any) {
      this.message = ''
      this.message = text
    }
  }
})
