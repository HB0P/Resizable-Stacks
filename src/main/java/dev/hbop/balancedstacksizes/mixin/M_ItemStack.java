package dev.hbop.balancedstacksizes.mixin;

import dev.hbop.balancedstacksizes.config.ModConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ItemStack.class)
public abstract class M_ItemStack {

    @Shadow public abstract RegistryEntry<Item> getRegistryEntry();

    @Inject(
            method = "getMaxCount",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getMaxCount(CallbackInfoReturnable<Integer> cir) {
        Optional<RegistryKey<Item>> registryKey = this.getRegistryEntry().getKey();
        if (registryKey.isEmpty()) return;
        Identifier identifier = registryKey.get().getValue();
        if (ModConfig.isStackSizeModified(identifier)) {
            cir.setReturnValue(ModConfig.getStackSize(identifier));
        }
    }
}