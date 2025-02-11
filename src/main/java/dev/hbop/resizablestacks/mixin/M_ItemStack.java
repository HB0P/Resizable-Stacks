package dev.hbop.resizablestacks.mixin;

import dev.hbop.resizablestacks.util.StackSizeHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class M_ItemStack {

    @Inject(
            method = "getMaxCount",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getMaxCount(CallbackInfoReturnable<Integer> cir) {
        int modifiedStackSize = StackSizeHelper.getModifiedStackSize((ItemStack) (Object) this);
        if (modifiedStackSize != -1) {
            cir.setReturnValue(modifiedStackSize);
        }
    }
}