package cosmeticx.client.command;

import cn.nukkit.command.Command;
import cn.nukkit.permission.Permission;
import cosmeticx.client.CosmeticX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubCommandManager {

    public HashMap<Command, List<SubCommand>> subCommands;
    public CosmeticX parentInstance;

    public SubCommandManager(CosmeticX instance) {
        this.subCommands = new HashMap<>();
        this.parentInstance = instance;
    }

    public HashMap<Command, List<SubCommand>> getSubCommands() {
        return subCommands;
    }

    public void registerSubCommand(Command owner, SubCommand subCommand) {
        if(!this.subCommands.containsKey(owner)) {
            this.subCommands.put(owner, new ArrayList<>());
        }
        this.subCommands.get(owner).add(subCommand);
        if(subCommand.permission != null) CosmeticX.getInstance().getServer().getPluginManager().addPermission(new Permission(subCommand.permission));
    }

    public boolean isRegistered(Command command, String subCommandName) {
        return getSubCommandByName(command, subCommandName) != null;
    }

    public List<SubCommand> getSubCommands(Command command) {
        return this.subCommands.get(command);
    }

    public SubCommand getSubCommandByName(Command command, String subCommandName) {
        for(SubCommand subCommand : getSubCommands(command)) {
            if(subCommandName.equals(subCommand.getName()))
                return subCommand;
        }
        return null;
    }

    public CosmeticX getParentInstance() {
        return parentInstance;
    }
}
