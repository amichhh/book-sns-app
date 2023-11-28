# vue-book-app

バックエンドにSpring Boot、フロントエンドにVue.jsを使用した読書記録共有アプリです。

## フロントエンドディレクトリ構成
```
src/
　├─ assets/
　├─ api/
　│　　├─ admin/ ...管理ユーザーでログインした際に使用
　│　　├─ visitor/ ...ゲストユーザーで訪問 or 一般ユーザーでログインした際に使用
　│　　└─ axios.ts ...API通信を行うaxiosインスタンスを定義
　├─ ui/
　│　　└─ components/
　│　　　　├─ modules/ ...各ページで使用する部品を設定する
　│　　　　│　　├─ admin/ ...管理ユーザーでログインした際に使用
　│　　　　│　　│　　├─ book/
　│　　　　│　　│　　├─ record/ ...公開されている読書記録に対してのみ操作可能
　│　　　　│　　│　　└─ account/
　│　　　　│　　└─ visitor/ ...ゲストユーザーで訪問 or 一般ユーザーでログインした際に使用
　│　　　　│　　　　 ├─ top/
　│　　　　│　　　　 ├─ book/
　│　　　　│　　　　 ├─ ranking/
　│　　　　│　　　　 ├─ about/
　│　　　　│　　　　 └─ mypage/
　│　　　　├─ templates/ ...各ページのレイアウトを設定する & APIを呼び出す
　│　　　　│　　├─ admin/
　│　　　　│　　│　　├─ book/
　│　　　　│　　│　　├─ record/ ...公開されている読書記録に対してのみ操作可能
　│　　　　│　　│　　└─ account/
　│　　　　│　　├─ visitor/
　│　　　　│　　│　　├─ top/
　│　　　　│　　│　　├─ book/
　│　　　　│　　│　　├─ ranking/
　│　　　　│　　│　　├─ about/
　│　　　　│　　│　　└─ mypage/
　│　　　　│　　├─ Admin.vue
　│　　　　│　　└─ Visitor.vue
　│　　　　└─ pages/ ...templatesを配置する土台
　│　　　　　　 ├─ admin/  
　│　　　　　　 ├─ visitor/
　│　　　　　　 ├─ Admin.vue
　│　　　　　　 └─ Visitor.vue
　├─ router/ ...ルーティングを定義
　├─ stores/ ...ログイン情報を保持しておく
　└─ types/ ...型を定義
```

## 依存関係
```
pages ─> templates ─> modules
　　　　　　　└─> api     └─> api
router ─> pages
```