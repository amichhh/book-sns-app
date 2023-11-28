import { api } from '@/api/axios'
import type { Account, LoginInfo } from '@/types'

export const login = (param: Object): Promise<Account> => {
  return api().post('login', param)
}

export const logout = (): Promise<void> => {
  return api().post('logout')
}

export const getLoginInformation = (): Promise<LoginInfo> => {
  return api().get('/auth/login/information')
}
