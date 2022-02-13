/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.listener;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cosmeticx.CosmeticX;
import cosmeticx.api.ApiRequest;
import cosmeticx.cosmetics.manager.CosmeticManager;
import cosmeticx.cosmetics.session.CosmeticSession;
import cosmeticx.utils.Utils;

import java.util.Map;

public class PlayerPreLoginListener implements Listener {

    public void onCreation(PlayerPreLoginEvent event) {
        //todo: waterdogpe xuid
        Player player = event.getPlayer();
        String xuid = player.getLoginChainData().getXUID();
        CosmeticSession session = CosmeticManager.getInstance().addSession(player.getName(), player.getSkin());

        ApiRequest apiRequest = new ApiRequest("/users/cosmetics/" + xuid, true);
        apiRequest.body("skinData", Utils.encodeSkinData(player.getSkin().getSkinData().data));
        apiRequest.body("geometry_data", player.getSkin().getGeometryData());
        apiRequest.body("geometry_name", ""); //todo: how i get Skin#getGeometryName in Nukkit?

        CosmeticX.getInstance().sendRequest(apiRequest, apiRequestResult -> {
            Map<String, String> response = apiRequestResult.messageAsMap();
            //session.setLegacySkin();
            session.sendSkin(response.get("buffer"), response.get("geometry_name"), response.get("geometry_data"));
        }, e -> {
            CosmeticX.getInstance().getLogger().error("Something went wrong!");
        }, 5);
    }
}
