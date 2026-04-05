# Notebook for Android

Android用統合システム手帳アプリケーション。

## 概要
本プロジェクトは **OrbitのAndroidアプリケーションの開発テスト** として作成されました。
プレミアムなデザインと多機能性を備えた、Androidネイティブのシステム手帳アプリを目指しています。

## 開発情報
- **開発開始日時**: 2026年4月5日 22:08 (JST)
- **アーキテクチャ**: MVVM (Model-View-ViewModel) + Repository パターン
- **技術スタック**:
  - **言語**: Kotlin
  - **UI**: Jetpack Compose (Declarative UI)
  - **非同期処理**: Coroutines, Flow
  - **依存注入**: Hilt (Dagger)
  - **データベース**: Firebase Firestore (Real-time Sync)

## 実装機能
- [x] メモ機能（Firestoreによるクラウド同期）
- [x] 電卓機能（統合インターフェース）
- [ ] カレンダー・スケジュール管理（予定）
- [ ] ToDoリスト（予定）
- [ ] Googleアカウント連携（予定）
- [ ] Googleカレンダー連携（予定）

## システム構造
詳細は [DATA_FLOW.md](DATA_FLOW.md) を参照してください。

## 著作権・ライセンス
© 2026 k1031oct. All rights reserved.
