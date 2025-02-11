package dev.hbop.resizablestacks.util;

import dev.hbop.resizablestacks.ResizableStacks;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

import java.util.Map;

@Config(name = ResizableStacks.MOD_ID, wrapperName = "ModConfig")
public class ModConfigModel {
    
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public Map<String, Integer> stackSizes = Map.of();
}