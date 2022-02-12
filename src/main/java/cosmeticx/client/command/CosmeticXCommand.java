package cosmeticx.client.command;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import cosmeticx.client.CosmeticX;
import cosmeticx.client.command.subcommand.HelpCommand;
import cosmeticx.client.command.subcommand.InfoSubCommand;
import cosmeticx.client.command.subcommand.MenuSubCommand;
import cosmeticx.client.command.subcommand.ReloadSubCommand;

public class CosmeticXCommand extends Command {

    public CosmeticXCommand() {
        super("cosmeticx");
        CosmeticX.getInstance().getSubCommandMap().registerSubCommand(this, new HelpCommand());
        CosmeticX.getInstance().getSubCommandMap().registerSubCommand(this, new InfoSubCommand());
        CosmeticX.getInstance().getSubCommandMap().registerSubCommand(this, new MenuSubCommand());
        CosmeticX.getInstance().getSubCommandMap().registerSubCommand(this, new ReloadSubCommand());
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(args.length < 1) {
            sender.getServer().dispatchCommand(sender, "cosmeticx help");
            return true;
        }

        String subCommandName = args[0];
        SubCommandManager manager = CosmeticX.getInstance().getSubCommandMap();
        if(!manager.isRegistered(this, subCommandName)) {
            sender.sendMessage(CosmeticX.PREFIX + TextFormat.RED + "Unknown command. Please use /cosmeticx help");
            return true;
        }

        SubCommand subCommand = manager.getSubCommandByName(this, subCommandName);

        if(subCommand.permission != null) {
            if(!sender.hasPermission(subCommand.permission)) {
                sender.sendMessage(CosmeticX.PREFIX + TextFormat.RED + "You have no permissions to use this command!");
                return true;
            }
        }
        subCommand.execute(sender, this, args);
        return true;
    }
}
