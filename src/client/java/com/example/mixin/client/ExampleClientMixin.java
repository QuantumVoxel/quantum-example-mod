package com.example.mixin.client;

import com.example.ExampleMod;
import dev.ultreon.quantum.client.QuantumVoxel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(QuantumVoxel.class)
public class ExampleClientMixin {
	@Inject(at = @At("RETURN"), method = "create")
	private void init(CallbackInfo info) {
		// This code is injected into the start of QuantumVoxel.render()V
		ExampleMod.LOGGER.info("Hello World from Quantum Voxel!");
	}
}