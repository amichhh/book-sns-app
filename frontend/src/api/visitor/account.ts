import { api } from '@/api/axios'
import type { Account } from '@/types'

export const findAccount = (): Promise<Account> => {
  return api().get('/account')
}

export const registerAccount = (param: Object): Promise<Account> => {
  return api().post('/account', param)
}

export const editAccount = (param: Object): Promise<Account> => {
  return api().post('/account/edit', param)
}
