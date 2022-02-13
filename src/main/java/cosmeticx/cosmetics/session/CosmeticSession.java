package cosmeticx.cosmetics.session;

import cn.nukkit.Player;

public class CosmeticSession {

    protected Player holder;

    public CosmeticSession(Player holder) {
        this.holder = holder;
    }

    public Player getHolder() {
        return holder;
    }
}
