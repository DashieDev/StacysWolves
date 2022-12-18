package stacywolves.common.utils;

import com.mojang.math.Vector3d;

import net.minecraft.util.Mth;

public class Maths {
    public static Vector3d getFacing(float rotationYaw, float rotationPitch) {
		float vx = -Mth.sin(rad(rotationYaw)) * Mth.cos(rad(rotationPitch));
		float vz = Mth.cos(rad(rotationYaw)) * Mth.cos(rad(rotationPitch));
		float vy = -Mth.sin(rad(rotationPitch));
		return new Vector3d(vx, vy, vz);
	}

    public static float rad(float angle) {
		return angle * (float) Math.PI / 180;
	}
}
