
# CLI RPG
## 概要
Javaの学習用に作成した実験的なRPGゲームです．
macOSでのみ動作を確認済です．

## 機能
- ドラゴンとの戦闘ができます．
- アイテムを購入・使用できます．
- MP, HPの概念があります．
- ターミナル上で動作します．

## 操作画面
![[battle]](assets/battle.png)
*BattleSessionの様子．*

![[shop]](assets/shop.png)
*ShopSessionの様子*

![[status]](assets/status.png)
*PlayerItemListSessionの様子*

## 実装
Session抽象クラスを中心に設計されています．主なクラスは以下の通りです．
- Session: 
  - ゲーム内のビジネスロジックと，ターミナルへの描画を担当．
  - セッションの概念を提供．
  - MainSession: メイン画面．
  - BattleSession: 戦闘画面．
  - ShopSession: ショップ画面．
- Command: 
  - 各セッション画面で表示される，選択可能な項目を担当．
  - ActionCommandAdapter: AdapterパターンでActionをCommandに変換するための具象クラス．
  - QuietCommand: セッション終了．
- Item: 全てのアイテムの抽象クラス．
  - Armor: 防具の抽象クラス．
  - Weapon: 武器の抽象クラス．
  - IronSword: 武器の具象クラス．
- Entity: プレイヤー，モンスター等の抽象クラス．
  - Player: プレイヤーの具象クラス．
  - Dragon: モンスターの具象クラス．
- Action: 戦闘における行動の抽象クラス．
  - Attack: 通常攻撃の抽象クラス．
  - Magic: 魔法攻撃の抽象クラス．
  - NormalAttack: 通常攻撃の具象クラス．
  - FireBall: 魔法攻撃の具象クラス．