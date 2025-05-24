package io.github.mcengine.common.backpack.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCEngineBackPackCommonTabCompleter implements TabCompleter {

    private static final String HEADS_PATH = "plugins/MCEngineBackPack/heads";

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("backpack")) return null;

        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // Suggest the main subcommands
            List<String> subcommands = Arrays.asList("create", "get");
            for (String sub : subcommands) {
                if (sub.startsWith(args[0].toLowerCase())) {
                    completions.add(sub);
                }
            }
            return completions;
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("get")) {
                // Suggest saved backpack names for "get"
                File folder = new File(HEADS_PATH);
                if (folder.exists() && folder.isDirectory()) {
                    File[] files = folder.listFiles((dir, name) -> name.endsWith(".yml"));
                    if (files != null) {
                        for (File file : files) {
                            String name = file.getName().replace(".yml", "");
                            if (name.toLowerCase().startsWith(args[1].toLowerCase())) {
                                completions.add(name);
                            }
                        }
                    }
                }
            }
            // Optionally suggest HDB id hints for "create" (if you have a fixed list or API integration)
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
            // Suggest typical backpack sizes (in rows)
            List<String> sizes = Arrays.asList("1", "2", "3", "4", "5", "6");
            for (String size : sizes) {
                if (size.startsWith(args[2])) {
                    completions.add(size);
                }
            }
        }

        return completions;
    }
}
