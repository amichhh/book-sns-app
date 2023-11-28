import Admin from '@/ui/components/pages/Admin.vue'
import AdminAccount from '@/ui/components/pages/admin/AdminAccount.vue'
import AdminBook from '@/ui/components/pages/admin/AdminBook.vue'
import AdminRecord from '@/ui/components/pages/admin/AdminRecord.vue'

import Visitor from '@/ui/components/pages/Visitor.vue'
import About from '@/ui/components/pages/visitor/About.vue'
import Book from '@/ui/components/pages/visitor/Book.vue'
import MyPage from '@/ui/components/pages/visitor/MyPage.vue'
import Ranking from '@/ui/components/pages/visitor/Ranking.vue'
import Top from '@/ui/components/pages/visitor/Top.vue'

import BookRead from '@/ui/components/pages/visitor/mypage/BookRead.vue'
import BookWant from '@/ui/components/pages/visitor/mypage/BookWant.vue'
import Menu from '@/ui/components/pages/visitor/mypage/Menu.vue'
import RecordLike from '@/ui/components/pages/visitor/mypage/RecordLike.vue'
import RecordList from '@/ui/components/pages/visitor/mypage/RecordList.vue'
import RecordWrite from '@/ui/components/pages/visitor/mypage/RecordWrite.vue'

import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // admin
    {
      path: '/admin',
      redirect: '/admin/book',
      component: Admin,
      children: [
        { path: '/admin/book', name: 'adminBook', component: AdminBook },
        { path: '/admin/record', name: 'adminRecord', component: AdminRecord },
        { path: '/admin/account', name: 'adminAccount', component: AdminAccount }
      ]
    },
    // visitor
    {
      path: '/',
      redirect: '/top',
      component: Visitor,
      children: [
        { path: '/top', name: 'top', component: Top },
        { path: '/book', name: 'book', component: Book },
        { path: '/ranking', name: 'ranking', component: Ranking },
        { path: '/about', name: 'about', component: About },
        {
          path: '/mypage',
          redirect: '/mypage/menu',
          component: MyPage,
          children: [
            { path: '/mypage/menu', name: 'menu', component: Menu },
            { path: '/mypage/record/write', name: 'write', component: RecordWrite },
            { path: '/mypage/record/list', name: 'list', component: RecordList },
            { path: '/mypage/record/like', name: 'like', component: RecordLike },
            { path: '/mypage/book/read', name: 'read', component: BookRead },
            { path: '/mypage/book/want', name: 'want', component: BookWant }
          ]
        }
      ]
    },
    { path: '/*', component: { path: '/top' } }
  ]
})

export default router
