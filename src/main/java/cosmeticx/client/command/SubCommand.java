package cosmeticx.client.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public abstract class SubCommand {

    public String permission = null;

    public final String name;

    public SubCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return "";
    }

    public abstract void execute(CommandSender sender, Command parent, String[] args);
}
