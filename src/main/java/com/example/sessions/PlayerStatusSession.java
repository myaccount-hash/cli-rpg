package com.example.sessions;

import com.example.commands.Command;
import com.example.commands.QuitCommand;
import com.example.entities.Entity;
import com.example.entities.Entity.ItemBox.ItemCount;
import com.example.utils.Utils;

/*
 * Playerの所有するItemの一覧を表示するセッション．
 * メニューからItemを選ぶとCommandSessionに推移しCommandを選択できる．
 */
public class PlayerStatusSession extends Session {

  public PlayerStatusSession(Session parentSession, Entity sessionOwner) {
    super("ItemList", "所持アイテム一覧", parentSession, sessionOwner);
    updateDisplayText();
  }

  @Override
  protected void afterCommandExecuted() {
    updateDisplayText();
    updateMenu();
  }

  private void updateDisplayText() {
    setDisplayText(
        sessionOwner.getInfoText()
            + "\n"
            + Utils.format("所持金", 8)
            + ": "
            + sessionOwner.getGold()
            + "G");
  }

  @Override
  protected void updateMenu() {
    this.commands.clear();
    this.commandNames.clear();

    // ItemBoxに集計処理を委譲
    for (ItemCount itemCount : sessionOwner.getItemBox().getItemCounts().values()) {
      addCommand(
          new Command(itemCount.getDisplayName(), itemCount.item.getDescription()) {
            @Override
            public boolean execute() {
              new ItemCommandSession(itemCount.item, PlayerStatusSession.this, sessionOwner)
                  .run();
              return true;
            }
          });
    }

    addCommand(new QuitCommand(this));
  }
}
