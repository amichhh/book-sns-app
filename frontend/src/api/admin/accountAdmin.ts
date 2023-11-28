import { api } from '@/api/axios'
import type { Account } from '@/types'

export const findAccount = (): Promise<Account[]> => {
  return api().get('/admin/account')
}

export const registerAccount = (param: Object): Promise<Account> => {
  return api().post('/admin/account', param)
}
