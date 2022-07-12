package com.falsepattern.endlessids.mixin.mixins.client.biomewand;

import com.falsepattern.endlessids.mixin.helpers.IChunkMixin;
import com.spacechase0.minecraft.biomewand.BiomeWandMod;
import com.spacechase0.minecraft.biomewand.item.BiomeWandItem;
import lombok.val;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

@Mixin(value = BiomeWandItem.class,
       remap = false)
public abstract class BiomeWandItemMixin {
    /**
     * @author FalsePattern
     * @reason Biome ID extension
     */
    @Overwrite
    public int getColorFromItemStack(ItemStack stack, int pass) {
        NBTTagCompound tag = stack.getTagCompound();
        int biomeID = -1;
        if (tag != null && tag.hasKey("sampledBiomeS")) {
            biomeID = Short.toUnsignedInt(tag.getShort("sampledBiomeS"));
        }
        if (pass != 1) {
            return -1;
        }
        if (biomeID < 0) {
            return 0;
        }
        val biome = BiomeGenBase.getBiomeGenArray()[biomeID];
        if (biome == null) {
            return 0;
        }
        return biome.color;
    }
}
