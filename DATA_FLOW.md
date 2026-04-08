<!-- ORBIT_ID: 1775394498379 -->
<!-- ORBIT_NAME: Notebook for Android -->
<!-- ORBIT_PATH: C:\Engineering\Notebook for Android -->

# DATA_FLOW for Notebook for Android (Native)

## システム構造 (Architecture Overview - Kotlin Native)
MVVM (Model-View-ViewModel) + Repository パターンを採用し、Firebase Firestore をデータソースとして使用します。

└─ app/src/main/java/com/k1031oct/nfa/
   ├─ core/ (共通型定義、DIモジュール)
   │  └─ di/ (Hilt モジュール)
   ├─ data/ (データ層)
   │  ├─ models/ (Note.kt: tags, imageUrl, isLocked を含む拡張モデル)
   │  └─ repositories/ (NoteRepository.kt)
   ├─ ui/ (UI層)
   │  ├─ viewmodels/ (NoteViewModel.kt)
   │  ├─ states/ (NoteUiState.kt)
   │  └─ screens/ (MainScreen.kt, EditorScreen.kt, HistoryScreen.kt)
   ├─ util/ (BiometricHelper.kt)
   └─ MainActivity.kt (FragmentActivity 継承)

## データフロー (Data Flow)

| Actor | Role | Responsibilities |
| :--- | :--- | :--- |
| **Firestore** | Remote Data Source | クラウド上のノートデータの永続化。リアルタイム同期。 |
| **NoteRepository** | Data Access | Firestore へのクエリ実行。`Flow<List<Note>>` でデータをストリーム提供。 |
| **NoteViewModel** | Logic / State | Repository からの Flow を監視し、`StateFlow` (UiState) に変換。ユーザー操作の処理。 |
| **NoteUiState** | Data Container | UI が参照する状態（ノート、フォルダ、履歴、**検索クエリ**、読込状態）。 |
| **BiometricHelper** | Security Layer | 機密フォルダへのアクセス時に指紋・顔認証を介在させる。 |
| **Compose Screen** | Presentation | `UiState` を収集してレンダリング。**Coil** による画像表示に対応。 |

### ノートの取得フロー
1. `NoteViewModel` が初期化時に `NoteRepository.getNotes()` を呼び出し。
2. `NoteRepository` が Firestore の `notes` コレクションを購読 (snapshots)。
3. データ変更を検知すると、`NoteViewModel` の `_uiState` (MutableStateFlow) が更新される。
4. `MainScreen` (Compose) が自動的に再描画。

### ノートの追加・編集フロー
1. ユーザーが保存ボタンをタップ。
2. `EditorScreen` から `NoteViewModel.saveNote(note)` を呼び出し。
3. `NoteViewModel` は Coroutine スコープ内で `NoteRepository.updateNote(note)` を実行。
4. `NoteRepository` が Firestore に `set()` または `add()` を実行。

### 検索・フィルタリングフロー [NEW]
1. `MainScreen` の検索バーでユーザーが入力。
2. `NoteViewModel.updateSearchQuery(query)` を呼び出し、`NoteUiState` が更新。
3. `MainScreen` 内で `uiState.notes` に対して `title`, `content`, `tags` のフィルタリングを適用して表示。

### セキュリティアクセスフロー [NEW]
1. ユーザーが `isLocked = true` のフォルダを選択。
2. `MainScreen` が `BiometricHelper` を呼び出し認証プロンプトを表示。
3. `onSuccess` コールバックがトリガーされて初めてフォルダの内容が表示される。
