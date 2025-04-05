package com.terraformersmc.modmenu.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import net.minecraft.GameSettings;
import net.minecraft.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(GameSettings.class)
public class MixinGameSettings {
	@Shadow public KeyBinding[] keyBindings;

	@WrapOperation(
		method = "<init>(Lnet/minecraft/Minecraft;Ljava/io/File;)V",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/GameSettings;loadOptions()V",
			opcode = Opcodes.PUTFIELD
		)
	)	public void registerWailaKeybindings(GameSettings instance, Operation<Void> original) {
		new ModMenuEventHandler();
		ModMenuEventHandler.register();
		List<KeyBinding> list = new ArrayList<>();
		list.add(ModMenuEventHandler.MENU_KEY_BIND);
		this.keyBindings = ArrayUtils.addAll(this.keyBindings, list.toArray(KeyBinding[]::new));
		original.call(instance);
	}
}
