/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.ConsoleCommandSender;
import cn.nukkit.lang.TranslationContainer;
import cn.nukkit.utils.TextFormat;
import cosmeticx.CosmeticX;
import cosmeticx.command.subcommand.*;

import java.util.Arrays;
import java.util.HashMap;

public class CosmeticXCommand extends Command {
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();
    private final HashMap<String, SubCommand> aliasSubCommands = new HashMap<>();

    public CosmeticXCommand() {
        super("cosmeticx", "Cosmetic-X command", "§cUsage: §7/cosmeticx help", new String[]{"cx"});
        setPermission("cosmetics-x.command");
        loadSubCommand(new HelpSubCommand("help", "Help sub-command.", new String[]{"?"}));
        loadSubCommand(new InfoSubCommand("info", "Info sub-command.", new String[]{"i"}));
        loadSubCommand(new ReloadSubCommand("reload", "Reload sub-command.", new String[]{"rl"}));
        loadSubCommand(new MenuSubCommand("menu", "Shows public cosmetics menu.", new String[]{"public"}));
        loadSubCommand(new SlotCosmeticsMenuSubCommand("slot", "Shows slot cosmetics menu.", new String[]{"server"}));
        //loadSubCommand(new EncodeCommand("slot", "Shows slot cosmetics menu.", new String[]{"server"}));// NOTE: This command is dev-only
        loadSubCommand(new PermissionsSubCommand("permissions", "Shows all permissions.", new String[]{"perms"}));
    }

    public HashMap<String, SubCommand> getSubCommands() {
        return subCommands;
    }

    public void loadSubCommand(SubCommand subCommand) {
        subCommands.put(subCommand.getName(), subCommand);
        for (String alias : subCommand.getAliases()) {
            if (alias != null && !alias.equals("")) {
                aliasSubCommands.put(alias, subCommand);
            }
        }
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(getUsage());
        } else {
            String subCommandName = args[0].toLowerCase();
            SubCommand subCommand;
            if (subCommands.containsKey(subCommandName)) {
                subCommand = subCommands.get(subCommandName);
            } else if (aliasSubCommands.containsKey(subCommandName)) {
                subCommand = aliasSubCommands.get(subCommandName);
            } else {
                sender.sendMessage("Unknown sub-command '{$subCommandName}'.");
                return true;
            }
            if (subCommand instanceof PlayerSubCommand && !(sender instanceof Player)) {
                sender.sendMessage(TextFormat.RED + "Sub-command can only executed by players.");
                return true;
            }
            if (subCommand instanceof ConsoleSubCommand && !(sender instanceof ConsoleCommandSender)) {
                if (sender.isOp()) {
                    sender.sendMessage(TextFormat.RED + "Sub-command can only executed in console.");
                }
                return true;
            }
            if (subCommand.getPermission() != null && !sender.hasPermission(getPermission() + "." + subCommand.getPermission())) {
                sender.sendMessage(new TranslationContainer(TextFormat.RED + "%commands.generic.unknown", this.getName()));
                return true;
            }
            try {
                subCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
            } catch (Throwable e) {
                sender.sendMessage(TextFormat.RED + "Error while executing sub-command '" + subCommand.getName() + "'");
                CosmeticX.getInstance().getLogger().error("Error while executing sub-command " + subCommand.getName(), e);
            }
        }
        return true;
    }

    @Override
    public boolean unregister(CommandMap commandMap) {
        return false; //NOTE: can't overwrite this command
    }
}
