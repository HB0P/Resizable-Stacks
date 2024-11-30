package dev.hbop.balancedstacksizes.config;

import dev.hbop.balancedstacksizes.BalancedStackSizes;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.util.Identifier;

import java.util.Map;

@Config(name = BalancedStackSizes.MOD_ID)
public class ModConfig implements ConfigData {
    
    public Map<String, Integer> stackSizes = Map.of();

    public static boolean isStackSizeModified(Identifier identifier) {
        return getConfig().stackSizes.containsKey(identifier.toString());
    }

    public static int getStackSize(Identifier identifier) {
        int stackSize = getConfig().stackSizes.get(identifier.toString());
        return Math.min(stackSize, BalancedStackSizes.MAX_STACK_SIZE);
    }
    
    private static ModConfig getConfig() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}