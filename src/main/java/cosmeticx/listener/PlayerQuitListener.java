/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.listener;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;
import com.google.gson.Gson;
import cosmeticx.CosmeticX;
import cosmeticx.api.ApiRequest;
import cosmeticx.cosmetics.manager.CosmeticManager;
import cosmeticx.cosmetics.session.CosmeticSession;

public class PlayerQuitListener implements Listener {

    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        CosmeticSession session = CosmeticManager.getInstance().getSession(player.getName());
        String xuid = player.getLoginChainData().getXUID();

        ApiRequest apiRequest = new ApiRequest("/users/cosmetics/" + xuid, true);
        apiRequest.body("active", new Gson().toJson(session.getActiveCosmetics()));
        CosmeticX.getInstance().sendRequest(apiRequest, apiRequestResult -> {
            CosmeticManager.getInstance().deleteSession(playerName);
        }, e -> {}, 5);
    }
}
