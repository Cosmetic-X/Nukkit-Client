/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.listener;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChangeSkinEvent;
import cn.nukkit.utils.TextFormat;

public class PlayerChangeSkinListener implements Listener {

    public void onSkinChange(PlayerChangeSkinEvent event) {
        event.setCancelled();
        event.getPlayer().sendMessage(TextFormat.RED + "Skin changing is not implemented yet.");
    }
}
