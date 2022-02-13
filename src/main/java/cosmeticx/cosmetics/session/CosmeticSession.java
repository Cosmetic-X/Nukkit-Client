/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.cosmetics.session;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.data.Skin;
import cosmeticx.utils.Utils;
import lombok.Getter;

import java.util.HashMap;

@Getter
public final class CosmeticSession {
    private final String username;
    private Skin legacySkin;
    private Player holder;
    private HashMap<Integer, String> activeCosmetics = new HashMap<>();

    public CosmeticSession(String username, Skin legacySkin) {
        this.username = username;
        this.legacySkin = legacySkin;
    }

    public Player getHolder() {
        return holder == null ? holder = Server.getInstance().getPlayerExact(username) : holder;
    }

    public void sendSkin(String buffer, String geometry_name, String geometry_data){
        if (geometry_name == null) {
            //geometry_name = getHolder().getSkin().getGeometryName();// TODO
        }
        if (geometry_data == null) {
            geometry_data = getHolder().getSkin().getGeometryData();
        }
        Skin skin = new Skin();
        skin.setSkinId(getHolder().getSkin().getSkinId());
        skin.setSkinData(Utils.decodeSkinData(buffer));
        skin.setGeometryName(geometry_name);
        getHolder().setSkin(skin);
    }

    public void setLegacySkin(Skin legacySkin) {
        this.legacySkin = legacySkin;
    }

    public Skin getLegacySkin() {
        return legacySkin;
    }
}
