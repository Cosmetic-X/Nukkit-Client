package cosmeticx.command.subcommand;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cosmeticx.command.PlayerSubCommand;
import cosmeticx.command.SubCommand;

public class MenuSubCommand extends PlayerSubCommand {
    public MenuSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    public void execute(CommandSender sender, String[] args) {
        //todo: send cosmetic menu form
    }
}
