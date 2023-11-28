import { useMessageStore } from '@/stores/message'
import type { ErrorResponseData } from '@/types'
import type { AxiosError, AxiosInstance } from 'axios'
import Axios from 'axios'

// バックエンドAPI
export const api = () => {
  const axios: AxiosInstance = Axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
      'Content-Type': 'application/json'
    },
    withCredentials: true
  })
  axios.interceptors.response.use(
    (res) => {
      return res.data
    },
    (err: AxiosError) => {
      const res = err.response
      const message = useMessageStore()
      if (res) {
        switch (res.status) {
          case 400:
            message.setMessage('入力内容を確認してください。')
            break
          case 403:
            message.setMessage('操作が許可されていません。')
            break
          case 500: {
            const data = res.data as ErrorResponseData
            message.setMessage(data.message)
            break
          }
        }
      }
      return Promise.reject(err)
    }
  )
  return axios
}

// 楽天ブックス書籍検索API
export const apiClientRakutenBooks: AxiosInstance = Axios.create({
  baseURL: import.meta.env.VITE_RAKUTENBOOKS_API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
  }
})
