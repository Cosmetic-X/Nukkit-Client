package cosmeticx.client.command.subcommand;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cosmeticx.client.CosmeticX;
import cosmeticx.client.command.SubCommand;

public class HelpCommand extends SubCommand {

    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(CommandSender sender, Command parent, String[] args) {
        StringBuilder message = new StringBuilder();
        message.append(CosmeticX.PREFIX).append(TextFormat.GRAY).append("Help list of CosmeticX: ");
        for(SubCommand subCommand : CosmeticX.getInstance().getSubCommandMap().getSubCommands(parent)) {
            message.append("\n" + "/cosmeticx ").append(subCommand.getName())
                    .append(" ").append(TextFormat.DARK_GRAY)
                    .append("| ").append(TextFormat.GRAY).append(subCommand.getDescription());
        }

        sender.sendMessage(message.toString());
    }

    @Override
    public String getDescription() {
        return "List of CosmeticX Commands";
    }
}
