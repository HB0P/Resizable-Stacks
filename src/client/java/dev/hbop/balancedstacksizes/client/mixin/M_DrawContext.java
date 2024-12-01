package dev.hbop.balancedstacksizes.client.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawContext.class)
public abstract class M_DrawContext {

    @Shadow @Final private MatrixStack matrices;

    @Inject(
            method = "drawStackCount",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V",
                    shift = At.Shift.AFTER
            )
    )
    private void drawStackCount(TextRenderer textRenderer, ItemStack stack, int x, int y, String stackCountText, CallbackInfo ci) {
        String string = stackCountText == null ? String.valueOf(stack.getCount()) : stackCountText;
        if (string.length() >= 4) {
            this.matrices.scale(0.5f, 0.5f, 1);
            this.matrices.translate(x + 15, y + 15, 0);
        }
    }
}