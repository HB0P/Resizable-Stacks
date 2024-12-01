package dev.hbop.balancedstacksizes.util;

import dev.hbop.balancedstacksizes.BalancedStackSizes;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

import java.util.Map;

@Config(name = BalancedStackSizes.MOD_ID, wrapperName = "ModConfig")
public class ModConfigModel {
    
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public Map<String, Integer> stackSizes = Map.of();
}