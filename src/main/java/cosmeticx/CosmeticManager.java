/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx;

public class CosmeticManager {
    private static CosmeticManager instance = null;

    public static CosmeticManager getInstance() {
        return instance == null ? new CosmeticManager() : instance;
    }

    public CosmeticManager() {
        instance = this;
    }
}
