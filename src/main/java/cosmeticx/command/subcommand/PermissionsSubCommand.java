package cosmeticx.command.subcommand;

import cn.nukkit.command.CommandSender;
import cn.nukkit.permission.Permission;
import cosmeticx.CosmeticX;
import cosmeticx.command.SubCommand;

public class PermissionsSubCommand extends SubCommand {
    public PermissionsSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
        setPermission("permission");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        for (Permission permission: CosmeticX.getInstance().getPermissions()) {
            sender.sendMessage("    " + permission.getName());
        }
    }
}
