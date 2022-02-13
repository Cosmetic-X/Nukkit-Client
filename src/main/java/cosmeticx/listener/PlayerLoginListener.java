/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.listener;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;
import cosmeticx.CosmeticX;
import cosmeticx.cosmetics.manager.CosmeticManager;
import cosmeticx.cosmetics.session.CosmeticSession;

public class PlayerLoginListener implements Listener {

    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        CosmeticSession session = CosmeticManager.getInstance().addSession(player.getName(), player.getSkin());
        if(session == null) {
            CosmeticX.getInstance().getLogger().emergency("Session is not initialized for " + player.getName());
            player.kick(CosmeticX.PREFIX + "Session is not initialized for " + player.getName());
        }
    }
}
