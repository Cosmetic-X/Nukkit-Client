/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.cosmetics.manager;

import cn.nukkit.entity.data.Skin;
import cosmeticx.cosmetics.Cosmetic;
import cosmeticx.cosmetics.CosmeticState;
import cosmeticx.cosmetics.session.CosmeticSession;
import cosmeticx.utils.Image;

import java.util.HashMap;
import java.util.List;

public class CosmeticManager {

    private static CosmeticManager instance = null;
    public static CosmeticManager getInstance() {
        return instance == null ? new CosmeticManager() : instance;
    }

    private HashMap<String, CosmeticSession> sessions;
    private List<Cosmetic> publicCosmetics;
    private List<Cosmetic> slotCosmetics;

    public CosmeticManager() {
        instance = this;
    }

    public void loadCosmetics() {
        //todo: load cosmetics
    }

    public void resetPublicCosmetics() {
        this.publicCosmetics.clear();
    }

    public void resetSlotCosmetics() {
        this.slotCosmetics.clear();
    }

    public void registerPublicCosmetic(String name, String display_name, String id, Image image) {
        this.publicCosmetics.add(new Cosmetic(name, display_name, id, CosmeticState.PUBLIC, image));
    }

    public void registerSlotCosmetic(String name, String display_name, String id, Image image) {
        this.slotCosmetics.add(new Cosmetic(name, display_name, id, CosmeticState.PUBLIC, image));
    }

    public CosmeticSession addSession(String userName, Skin legacySkin) {
        CosmeticSession cosmeticSession = new CosmeticSession(userName, legacySkin);
        this.sessions.put(userName, cosmeticSession);
        return cosmeticSession;
    }

    public void deleteSession(String userName) {
        this.sessions.remove(userName);
    }

    public CosmeticSession getSession(String userName) {
        if(!this.sessions.containsKey(userName)) return null;
        return this.sessions.get(userName);
    }

    public HashMap<String, CosmeticSession> getSessions() {
        return sessions;
    }

    public List<Cosmetic> getPublicCosmetics() {
        return publicCosmetics;
    }

    public List<Cosmetic> getSlotCosmetics() {
        return slotCosmetics;
    }
}
