import { api } from '@/api/axios'
import type { Record } from '@/types'

export const findRecord = (): Promise<Record[]> => {
  return api().get('/record')
}

export const findPublicRecord = (): Promise<Record[]> => {
  return api().get('/record/public')
}

export const findLikeRecord = (): Promise<Record[]> => {
  return api().get('/record/like')
}

export const registerRecord = (param: Object): Promise<Record> => {
  return api().post('/record', param)
}

export const editRecord = (param: Object): Promise<Record> => {
  return api().post('/record/edit', param)
}

export const likeRecord = (param: Object): void => {
  api().post('/record/like', param)
}

export const unlikeRecord = (param: Object): void => {
  api().post('/record/unlike', param)
}
