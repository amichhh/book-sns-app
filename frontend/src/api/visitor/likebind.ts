import { api } from '@/api/axios'
import type { LikeBind } from '@/types'

export const findMyLikeBind = (): Promise<LikeBind[]> => {
  return api().get('/likebind')
}
