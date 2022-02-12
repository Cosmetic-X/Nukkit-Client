package cosmeticx.client.command.subcommand;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginDescription;
import cn.nukkit.utils.TextFormat;
import cosmeticx.client.CosmeticX;
import cosmeticx.client.command.SubCommand;

public class InfoSubCommand extends SubCommand {
    public InfoSubCommand() {
        super("info");
    }

    @Override
    public void execute(CommandSender sender, Command parent, String[] args) {
        PluginDescription desc = CosmeticX.getInstance().getDescription();
        sender.sendMessage(CosmeticX.PREFIX + desc.getName());
        StringBuilder infoMessage = new StringBuilder();
        infoMessage
                .append(CosmeticX.PREFIX).append(desc.getName())
                .append("\n").append(TextFormat.DARK_AQUA).append("Token-Owner: ").append(TextFormat.WHITE).append(CosmeticX.getInstance().getHolder())
                .append("\n").append(TextFormat.DARK_AQUA).append("Version: ").append(TextFormat.WHITE).append(desc.getVersion())
                .append("\n").append(TextFormat.DARK_AQUA).append("Description: ").append(TextFormat.WHITE).append(desc.getDescription())
                .append("\n").append(TextFormat.DARK_AQUA).append("Authors: ").append(TextFormat.WHITE).append(String.join(", ", desc.getAuthors()));

        sender.sendMessage(infoMessage.toString());
    }

    @Override
    public String getDescription() {
        return "Get information about the client";
    }
}
