/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx;

import cn.nukkit.permission.Permission;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cosmeticx.command.CosmeticXCommand;
import cosmeticx.command.SubCommand;
import cosmeticx.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CosmeticX extends PluginBase {

    public static CosmeticX instance;

    private static String PROTOCOL = "https";
    private static String URL = "cosmetics-x.de";
    static String URL_API;
    private static int REFRESH_INTERVAL_TICKS = 1800;

    private String token = "TOKEN HERE";
    private String holder = "n/a";

    private CosmeticXCommand command;
    private Permission[] permissions;

    @Override
    public void onLoad() {
        instance = this;
        this.saveResource("TOKEN.txt");
        this.saveDefaultConfig();

        Config config = this.getConfig();

        PROTOCOL = config.getString("protocol", PROTOCOL);
        URL = config.getString("host", URL);
        int port = config.getInt("port", 20085);
        URL_API = PROTOCOL + "://" + CosmeticX.URL + ":" + port + "/api";
        REFRESH_INTERVAL_TICKS = this.getConfig().getInt("refresh-interval");

        try {
            token = Utils.file_get_contents("TOKEN.txt");
        } catch (IOException e) {
            this.getLogger().error("Token cannot be read");
            this.getPluginLoader().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {
        this.getServer().getCommandMap().register(getDescription().getName(), command = new CosmeticXCommand());
        registerPermissions();
        this.check();
    }

    @Override
    public void onDisable() {
    }

    private void registerPermissions() {
        ArrayList<Permission> permissions = new ArrayList<>();
        Permission overlord = new Permission("cosmetics-x.*", "Overlord permission");
        for (SubCommand subCommand : command.getSubCommands().values()) {
            if (subCommand.getPermission() != null) {
                permissions.add(new Permission(command.getPermission() + "." + subCommand.getPermission(), "Allows to use '/" + command.getName() + " " + subCommand.getName() + "' command.", Permission.DEFAULT_OP));
                overlord.getChildren().put(command.getPermission() + "." + subCommand.getPermission(), true);
            }
        }
        this.permissions = permissions.toArray(this.permissions);
    }

    public void reload() {
        //todo
    }

    public void refresh() {
        //todo
    }

    public void check() {
        if (this.token.equals("TOKEN HERE")) {
            this.getLogger().alert("Token is not set");
            return;
        }

        //todo
    }

    public void sendRequest(ApiRequest request, Consumer<ApiRequest> consumer) {
        request.header("token", this.token);

    }

    public String getHolder() {
        return holder;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    public static CosmeticX getInstance() {
        return instance;
    }

    public CosmeticXCommand getCosmeticXCommand() {
        return command;
    }
}
