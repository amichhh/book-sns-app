// ログイン情報
export type LoginInfo = {
  logined: boolean
  loginUser: Account
}

// アカウント
export type Account = {
  accountId: string
  userName: string
  password: string
  email: string
  authority: string
  status: string
}

// ログインフォーム
export type LoginForm = {
  userId: string
  password: string
}

// 書籍
export type Book = {
  id: number
  title: string
  author: string
  publisher: string
  outline: string
  genre: string[]
  isbn: string
  imagePath: string
  productPath: string
  recordCount: number
}

// 読書記録
export type Record = {
  id: number
  accountId: string
  bookId: number
  evaluation: number
  impression: string
  likeCount: number
  isPublic: boolean
  registerDate: Date
  updateDate: Date
}

// いいね紐付け
export type LikeBind = {
  id: number
  recordId: number
  accountId: string
}

export type PromiseType<T extends Promise<any>> = T extends Promise<infer P> ? P : never

export type ErrorResponseData = {
  error: string
  message: string
  path: string
  status: number
  timestamp: Date
  trace: string
  Prototype: Object
}

export const bookGenre = {
  ADVENTURE: '冒険',
  ECONOMIC: '経済',
  FANTASY: 'ファンタジー',
  HISTORY: '歴史',
  HORROR: 'ホラー',
  LIGHT_NOVEL: 'ライトノベル',
  MYSTERY: 'ミステリー',
  ROMANCE: '恋愛',
  SCIENCE_FICTION: 'SF',
  SUSPENSE: 'サスペンス',
  YOUTH: '青春'
}

export const authorityType = {
  ROLE_ADMIN: '管理ユーザー',
  ROLE_GENERAL: '一般ユーザー'
}

export const statusType = {
  VALID: '有効',
  INVALID: '無効'
}

// 楽天ブック
export type RakutenBook = {
  title: string
  titleKana: string
  subTitle: string
  subTitleKana: string
  seriesName: string
  seriesNameKana: string
  contents: string
  contentsKana: string
  author: string
  authorKana: string
  publisherName: string
  size: number
  isbn: string
  itemCaption: string
  salesDate: Date
  itemPrice: number
  listPrice: number
  discountRate: number
  discountPrice: number
  itemUrl: string
  affiliateUrl: string
  smallImageUrl: string
  mediumImageUrl: string
  largeImageUrl: string
  chirayomiUrl: string
  availability: number
  postageFlag: number
  limitedFlag: number
  reviewCount: number
  reviewAverage: number
  booksGenreId: string
}

// 楽天ブックス書籍検索API入力パラメーター
export type RakutenBooksInputParameter = {
  applicationId: string
  affiliateId: string | null
  format: string | null
  callback: string | null
  elements: string | null
  formatVersion: number | null
  title: string | null
  author: string | null
  publisherName: string | null
  size: number | null
  isbn: string | null
  booksGenreId: string | null
  hits: number | null
  page: number | null
  availability: number | null
  outOfStockFlag: number | null
  chirayomiFlag: number | null
  sort: string | null
  limitedFlag: number | null
  carrier: number | null
  genreInformationFlag: number | null
}

// 楽天ブックス書籍検索API出力パラメーター
export type RakutenBooksOutputParameter = {
  count: number
  page: number
  first: number
  last: number
  hits: number
  carrier: number
  pageCount: number
  Items: [
    {
      Item: {
        title: string
        titleKana: string
        subTitle: string
        subTitleKana: string
        seriesName: string
        seriesNameKana: string
        contents: string
        contentsKana: string
        author: string
        authorKana: string
        publisherName: string
        size: number
        isbn: string
        itemCaption: string
        salesDate: Date
        itemPrice: number
        listPrice: number
        discountRate: number
        discountPrice: number
        itemUrl: string
        affiliateUrl: string
        smallImageUrl: string
        mediumImageUrl: string
        largeImageUrl: string
        chirayomiUrl: string
        availability: number
        postageFlag: number
        limitedFlag: number
        reviewCount: number
        reviewAverage: number
        booksGenreId: string
      }
    }
  ]
  genreInformation: [
    // parent: {
    //   booksGenreId: string
    //   booksGenreName: string
    //   genreLevel: number
    // }
    // current: {
    //   booksGenreId: string
    //   booksGenreName: string
    //   itemCount: number
    //   genreLevel: number
    // }
    // children: {
    //   booksGenreId: string
    //   booksGenreName: string
    //   itemCount: number
    //   genreLevel: number
    // }
  ]
}
