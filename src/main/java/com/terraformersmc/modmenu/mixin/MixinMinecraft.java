package com.terraformersmc.modmenu.mixin;

import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import net.minecraft.Minecraft;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
	@Inject(method = "runTick", at = @At("TAIL"))
	private void onKeyEvent(CallbackInfo ci) {
		ModMenuEventHandler.onClientEndTick(ReflectHelper.dyCast(this));
	}
}
