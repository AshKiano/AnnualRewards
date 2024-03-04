package com.ashkiano.annualrewards;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Calendar;

public class AnnualCommandExecutor implements CommandExecutor {
    private final AnnualRewards plugin;

    public AnnualCommandExecutor(AnnualRewards plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getName();
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int lastClaimedYear = plugin.getConfig().getInt("rewards." + playerName + ".year", -1);

        if (currentYear == lastClaimedYear) {
            player.sendMessage("You have already claimed your annual reward.");
            return true;
        }

        String rewardCommand = plugin.getConfig().getString("reward-command").replace("%player%", playerName);
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), rewardCommand);
        player.sendMessage("You have claimed your annual reward.");

        plugin.getConfig().set("rewards." + playerName + ".year", currentYear);
        plugin.saveConfig();

        return true;
    }
}
