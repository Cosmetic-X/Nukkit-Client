/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command;

abstract public class PlayerSubCommand extends SubCommand{
    public PlayerSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    public PlayerSubCommand(String name, String description) {
        super(name, description);
    }

    public PlayerSubCommand(String name) {
        super(name);
    }

    public PlayerSubCommand(String name, String[] aliases) {
        super(name, aliases);
    }
}
