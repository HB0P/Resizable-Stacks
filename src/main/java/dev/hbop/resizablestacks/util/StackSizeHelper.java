package dev.hbop.resizablestacks.util;

import dev.hbop.resizablestacks.ResizableStacks;
import net.minecraft.item.ItemStack;

public class StackSizeHelper {

    public static final int MAX_STACK_SIZE = Integer.MAX_VALUE - 1;
    
    public static int getModifiedStackSize(ItemStack stack) {
        int stackSize = getRawModifiedStackSize(stack);
        if (stackSize <= 0) return -1;
        return Math.min(stackSize, MAX_STACK_SIZE);
    }
    
    private static int getRawModifiedStackSize(ItemStack stack) {
        for (String id : ResizableStacks.CONFIG.stackSizes().keySet()) {
            if (id.startsWith("#")) {
                if (stack.streamTags().anyMatch((tag) -> id.equals("#" + tag.id().toString()))) {
                    return ResizableStacks.CONFIG.stackSizes().get(id);
                }
            }
            else {
                if (id.equals(stack.getRegistryEntry().getIdAsString())) {
                    return ResizableStacks.CONFIG.stackSizes().get(id);
                }
            }
        }
        return -1;
    }
}