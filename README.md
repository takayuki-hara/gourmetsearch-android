# gourmetsearch-android
Androidの習得を目的とした飲食店検索アプリ

## Overview
グルメ検索アプリ。  
アーキテクチャ、Rx、Realmなどを試すのが主目的。

## Environment
- 言語：Kotlin 1.2.70
- IDE：AndroidStudio 3.1.4
- Gradle

## Stetho
Chromeで端末のデバッグを行えるツール。  
http://facebook.github.io/stetho/

セットアップして下記にアクセスると端末の内容が見られる。  
chrome://inspect/#devices

## Stetho_Realm
StethoでRealmのデータを閲覧できるようにするもの。  
あまり更新されてなくてRealmの最新には対応できていない（v3.5.0あたり）。  
https://github.com/uPhyca/stetho-realm

## メモ

■アプリ起動時にTextEditにフォーカスがあたっているのを回避する

→別のパーツに対してフォーカスを当ててしまう

```xml
<TextView
    android:focusable="true"
    android:focusableInTouchMode="true">
   <requestFocus /> 
</TextView>
```

## 課題

- お気に入りの変更をしても上タブ切り替えで更新されない（何らかのバインド機構を導入する必要がありそう）
