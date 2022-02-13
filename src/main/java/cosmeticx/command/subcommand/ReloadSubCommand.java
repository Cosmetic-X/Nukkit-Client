/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command.subcommand;

import cn.nukkit.command.CommandSender;
import cosmeticx.CosmeticX;
import cosmeticx.command.SubCommand;

public class ReloadSubCommand extends SubCommand {
    public ReloadSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
        setPermission("reload");
    }

    public void execute(CommandSender sender, String[] args) {
        CosmeticX.getInstance().reload();
    }
}
