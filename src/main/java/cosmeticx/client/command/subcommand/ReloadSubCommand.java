package cosmeticx.client.command.subcommand;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cosmeticx.client.CosmeticX;
import cosmeticx.client.command.SubCommand;

public class ReloadSubCommand extends SubCommand {
    public ReloadSubCommand() {
        super("reload");
        this.permission = "cosmeticx.reload";
    }

    @Override
    public void execute(CommandSender sender, Command parent, String[] args) {
        CosmeticX.getInstance().reload();
    }

    @Override
    public String getDescription() {
        return "Reload the client";
    }
}
