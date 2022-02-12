package cosmeticx.client.command.subcommand;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cosmeticx.client.command.SubCommand;

public class MenuSubCommand extends SubCommand {

    public MenuSubCommand() {
        super("menu");
    }

    @Override
    public void execute(CommandSender sender, Command parent, String[] args) {
        //todo: send cosmetic menu form
    }

    @Override
    public String getDescription() {
        return "Open the cosmetic menu";
    }
}
