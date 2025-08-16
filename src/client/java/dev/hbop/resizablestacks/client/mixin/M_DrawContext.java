package dev.hbop.resizablestacks.client.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawContext.class)
public abstract class M_DrawContext {

    @Shadow @Final private MatrixStack matrices;
    
    // change item count text to use suffixes
    @ModifyVariable(
            method = "drawStackCount",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private String changeStackCountText(String value) {
        if (value == null) return null;
        if (value.length() <= 3) return value;
        try {
            int count = Integer.parseInt(value);
            return String.valueOf(count);
        }
        catch (NumberFormatException e) {
            return value;
        }
    }

    // resize item count text
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