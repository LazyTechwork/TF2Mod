package ru.ivansteklow.tf2mod.enums;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum PickupTypeEnum {
	CRATE_OPEN("crate_open", 0, EnumParticleTypes.EXPLOSION_LARGE, new SoundEvent(new ResourceLocation("minecraft", "entity.generic.explode"))),
	HEALTH("health", 1, EnumParticleTypes.HEART, new SoundEvent(new ResourceLocation("minecraft", "entity.cat.purr"))),
	FOOD("food", 2, EnumParticleTypes.DRAGON_BREATH, new SoundEvent(new ResourceLocation("minecraft", "entity.player.burp")));
	
	private final String typeName;
	private final int typeId;
	private final EnumParticleTypes particle;
	private final SoundEvent sound;	
	
	private PickupTypeEnum(String typeName, int typeId, EnumParticleTypes particle, SoundEvent sound) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.particle = particle;
		this.sound = sound;
	}
	
	public String getPickupTypeName() {
		return this.typeName;
	}
	
	public int getPickupTypeId() {
		return this.typeId;
	}
	
	public EnumParticleTypes getPickupTypeParticle() {
		return this.particle;
	}
	
	public SoundEvent getPickupTypeSound() {
		return this.sound;
	}
	
}
