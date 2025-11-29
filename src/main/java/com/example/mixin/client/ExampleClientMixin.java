package com.example.mixin.client;

import com.example.ExampleMod;
import dev.ultreon.qvoxel.client.QuantumClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(QuantumClient.class)
public class ExampleClientMixin {
	@Inject(at = @At("RETURN"), method = "<init>")
	private void init(CallbackInfo info) {
		// This code is injected into the start of QuantumClient.<init>()V
		ExampleMod.LOGGER.info("Hello World from Quantum Voxel!");
	}
}