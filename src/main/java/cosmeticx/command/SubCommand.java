/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command;

import cn.nukkit.command.CommandSender;

public abstract class SubCommand {
    public final String name;
    public final String description;
    public String[] aliases;
    public String permission = null;


    public SubCommand(String name, String description, String[] aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    public SubCommand(String name, String description) {
        this.name = name;
        this.description = description;
        this.aliases = new String[0];
    }

    public SubCommand(String name) {
        this.name = name;
        this.description = "No description provided.";
        this.aliases = new String[0];
    }

    public SubCommand(String name, String[] aliases) {
        this.name = name;
        this.description = "No description provided.";
        this.aliases = aliases;
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public final String getPermission() {
        return permission;
    }

    public final void setPermission(String permission) {
        this.permission = permission;
    }

    public abstract void execute(CommandSender sender, String[] args);
}
