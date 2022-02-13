/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx;

import cn.nukkit.Player;
import cn.nukkit.permission.Permission;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cosmeticx.api.ApiRequest;
import cosmeticx.api.ApiRequestResult;
import cosmeticx.api.task.SendAsyncRequestTask;
import cosmeticx.command.CosmeticXCommand;
import cosmeticx.command.SubCommand;
import cosmeticx.cosmetics.manager.CosmeticManager;
import cosmeticx.listener.PlayerChangeSkinListener;
import cosmeticx.listener.PlayerLoginListener;
import cosmeticx.listener.PlayerPreLoginListener;
import cosmeticx.listener.PlayerQuitListener;
import cosmeticx.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CosmeticX extends PluginBase {

    private static CosmeticX instance;

    public static String PREFIX = TextFormat.DARK_PURPLE + "Cosmetic" + TextFormat.WHITE + "X" + " " + TextFormat.RESET;

    private static String PROTOCOL = "https";
    private static String URL = "cosmetics-x.de";
    public static String URL_API;
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
        this.reloadConfig();
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

        this.getLogger().notice("Reloaded config");
        this.check();
        this.refresh();
    }

    public void refresh() {
        CosmeticManager cosmeticManager = CosmeticManager.getInstance();

        cosmeticManager.resetSlotCosmetics();
        cosmeticManager.resetPublicCosmetics();
        cosmeticManager.loadCosmetics();
        this.getLogger().debug("Refreshed cosmetics.");
    }

    public void check() {
        if (this.token.equals("TOKEN HERE")) {
            this.getLogger().alert("Token is not set");
            return;
        }

        this.sendRequest(new ApiRequest("/", false), apiRequestResult -> {
            //todo: version check + auto update if config value -> true

            Map<String, String> response = apiRequestResult.messageAsMap();
            CosmeticX.getInstance().holder = response.get("holder");
            CosmeticX.getInstance().getLogger().notice("Logged in as " + CosmeticX.getInstance().holder);
            CosmeticManager.getInstance().loadCosmetics();
        }, apiRequest -> {
            CosmeticX.getInstance().getLogger().error("Login failed! Please check your token.");
            CosmeticX.getInstance().getPluginLoader().disablePlugin(CosmeticX.getInstance());
        }, 5);
    }

    public void sendRequest(ApiRequest request, Consumer<ApiRequestResult> consumer, Consumer<ApiRequest> errorConsumer, int timeout) {
        request.header("token", this.token);
        this.getServer().getScheduler().scheduleAsyncTask(this, new SendAsyncRequestTask(request, consumer, errorConsumer, timeout));
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

    private void initListener() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerChangeSkinListener(), this);
        pluginManager.registerEvents(new PlayerPreLoginListener(), this);
        pluginManager.registerEvents(new PlayerLoginListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }
}
