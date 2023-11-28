# Spring Security学習

## DB設計時のエラー
`User`はPostgreSQLの予約語で、テーブル作成時にエラーが発生。

## 認証時のエラー
パスワードがハッシュ化して登録されていないと、`o.s.s.c.bcrypt.BCryptPasswordEncoder     : Encoded password does not look like BCrypt`というエラーが出てログインに失敗する（401エラーが返される）ので注意。

## 認証後のAuthenticationオブジェクトの中身
```
{
  "authorities": [
    {
      "authority": "ADMIN"
    }
  ],
  "details": null,
  "authenticated": true,
  "principal": {
    "password": null,
    "username": "admin",
    "authorities": [
      {
        "authority": "ADMIN"
      }
    ],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "enabled": true,
    "account": {
      "userId": "admin",
      "userName": "admin",
      "password": "$2a$10$jierYO2/v0TzuwNHbLFbWuPH1OwiZiS0ZsS2.KMZfOog9g9ofElNK",
      "email": "admin@example.com",
      "authority": "ADMIN"
    }
  },
  "credentials": null,
  "name": "admin"
}
```