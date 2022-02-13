package cosmeticx.command.subcommand;

import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginDescription;
import cosmeticx.CosmeticX;
import cosmeticx.command.SubCommand;

public class InfoSubCommand extends SubCommand {
    public InfoSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    public void execute(CommandSender sender, String[] args) {
        PluginDescription desc = CosmeticX.getInstance().getDescription();
        sender.sendMessage("----- " + desc.getName() + " -----");
        sender.sendMessage("  Software: " + Server.getInstance().getName());
        sender.sendMessage("  Token-Owner: " + CosmeticX.getInstance().getHolder());
        sender.sendMessage("  Version: " + desc.getVersion());
        sender.sendMessage("  Description: " + desc.getDescription());
        sender.sendMessage("  Authors: " + String.join(", ", desc.getAuthors()));
    }
}
