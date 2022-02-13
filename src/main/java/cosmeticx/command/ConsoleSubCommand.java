/*
 * Copyright (c) Jan Sohn
 * All rights reserved.
 * This plugin is under GPL license
 */

package cosmeticx.command;

abstract public class ConsoleSubCommand extends SubCommand {
    public ConsoleSubCommand(String name, String description, String[] aliases) {
        super(name, description, aliases);
    }

    public ConsoleSubCommand(String name, String description) {
        super(name, description);
    }

    public ConsoleSubCommand(String name) {
        super(name);
    }

    public ConsoleSubCommand(String name, String[] aliases) {
        super(name, aliases);
    }
}
