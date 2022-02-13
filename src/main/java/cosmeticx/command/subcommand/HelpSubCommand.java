/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command.subcommand;

import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cosmeticx.CosmeticX;
import cosmeticx.command.SubCommand;

import java.util.StringJoiner;

public class HelpSubCommand extends SubCommand {
    public HelpSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        StringJoiner help = new StringJoiner("\n");
        for (SubCommand subCommand : CosmeticX.getInstance().getCosmeticXCommand().getSubCommands().values()) {
            help.add(TextFormat.GREEN + "/" + CosmeticX.getInstance().getCosmeticXCommand().getName() + " " + subCommand.getName() + " | " + subCommand.getDescription());
        }
        sender.sendMessage(TextFormat.GREEN + "--- " + CosmeticX.getInstance().getDescription().getName() + " - Help ---\n" + help);
    }
}
