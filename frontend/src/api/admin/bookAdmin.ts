import { api } from '@/api/axios'

export const deleteBook = (param: Object): void => {
  api().post('/admin/book/delete', param)
}
