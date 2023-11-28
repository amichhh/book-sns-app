import { api, apiClientRakutenBooks } from '@/api/axios'
import type { Book, RakutenBooksInputParameter, RakutenBooksOutputParameter } from '@/types'

export const loadBook = (param: Object): Promise<Book> => {
  return api().get('/book/load', { params: param })
}

export const findBook = (): Promise<Book[]> => {
  return api().get('/book')
}

export const findBookOrderByRecordCount = (): Promise<Book[]> => {
  return api().get('/book/ranking')
}

export const registerBook = (param: Object): Promise<Book> => {
  return api().post('/book', param)
}

export const editBook = (param: Object): Promise<Book> => {
  return api().post('/book/edit', param)
}

// 楽天ブックス書籍検索
export const findRakutenBooks = (param: RakutenBooksInputParameter) => {
  const books: Promise<RakutenBooksOutputParameter> = apiClientRakutenBooks
    .get('', { params: param })
    .then((res) => res.data)
    .catch((err) => console.log(err))
  return books
}
