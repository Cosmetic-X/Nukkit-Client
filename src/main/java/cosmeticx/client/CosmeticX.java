package cosmeticx.client;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import cosmeticx.client.api.ApiRequest;
import cosmeticx.client.command.CosmeticXCommand;
import cosmeticx.client.command.SubCommandManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class CosmeticX extends PluginBase {

    public static CosmeticX instance;

    public static final String PREFIX = TextFormat.DARK_PURPLE + "§lCosmetic" + TextFormat.WHITE + "X " + TextFormat.RESET;

    private static String PROTOCOL = "https";
    private static String URL = "cosmetic-x.de";
    static String URL_API;
    private static int REFRESH_INTERVAL_TICKS = 1800;

    private String token = "TOKEN HERE";
    private String holder = "n/a";

    private SubCommandManager subCommandMap;

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
            token = file_get_contents("TOKEN.txt");
        } catch (IOException e) {
            this.getLogger().error("Token cannot be read :/");
            this.getPluginLoader().disablePlugin(this);
        }

        subCommandMap = new SubCommandManager(this);
    }

    public SubCommandManager getSubCommandMap() {
        return subCommandMap;
    }

    @Override
    public void onEnable() {
        this.getServer().getCommandMap().register("cosmeticx", new CosmeticXCommand());
        this.check();
    }

    public void reload() {
        //todo
    }

    public void refresh() {
        //todo
    }

    public void check() {
        if(this.token.equals("TOKEN HERE")) {
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

    public static CosmeticX getInstance() {
        return instance;
    }

    private String file_get_contents(String filename) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder builder = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        return builder.toString();
    }
}
