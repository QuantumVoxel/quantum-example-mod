package com.example.mixin;

import com.example.ExampleMod;
import dev.ultreon.qvoxel.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Registries.class)
public class ExampleMixin {
	@Inject(at = @At("RETURN"), method = "<clinit>")
	private static void init(CallbackInfo info) {
		// This code is injected into the start of Registries.<clinit>()V
		ExampleMod.LOGGER.info("Hello World from Quantum Voxel!");
	}
}