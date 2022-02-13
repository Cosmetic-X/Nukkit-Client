/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command.subcommand;

import cn.nukkit.command.CommandSender;
import cosmeticx.command.PlayerSubCommand;

public class SlotCosmeticsMenuSubCommand extends PlayerSubCommand {
    public SlotCosmeticsMenuSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    public void execute(CommandSender sender, String[] args) {
        //todo: send private cosmetics menu form
    }
}
