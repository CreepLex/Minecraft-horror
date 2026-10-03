package com.creepLex.nightmare;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;

public class BackroomsGenerator {

    public static final int FLOOR_Y = 63;
    public static final int CEILING_Y = 69;

    public static void generateRoom(Level level, LevelChunk chunk) {

        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;

        int startX = chunkX * 16;
        int startZ = chunkZ * 16;

        // Padló
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                level.setBlock(
                        new BlockPos(startX + x, FLOOR_Y, startZ + z),
                        Blocks.YELLOW_WOOL.defaultBlockState(),
                        3
                );
            }
        }

        // Plafon
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                level.setBlock(
                        new BlockPos(startX + x, CEILING_Y, startZ + z),
                        Blocks.WHITE_CONCRETE.defaultBlockState(),
                        3
                );
            }
        }

        // Falak
        for (int y = FLOOR_Y + 1; y < CEILING_Y; y++) {

            // Északi fal
            for (int x = 0; x < 16; x++) {
                if (!hasNorthDoor(chunkX, chunkZ, x)) {
                    level.setBlock(
                            new BlockPos(startX + x, y, startZ),
                            Blocks.YELLOW_CONCRETE.defaultBlockState(),
                            3
                    );
                }
            }

            // Déli fal
            for (int x = 0; x < 16; x++) {
                if (!hasSouthDoor(chunkX, chunkZ, x)) {
                    level.setBlock(
                            new BlockPos(startX + x, y, startZ + 15),
                            Blocks.YELLOW_CONCRETE.defaultBlockState(),
                            3
                    );
                }
            }

            // Nyugati fal
            for (int z = 0; z < 16; z++) {
                if (!hasWestDoor(chunkX, chunkZ, z)) {
                    level.setBlock(
                            new BlockPos(startX, y, startZ + z),
                            Blocks.YELLOW_CONCRETE.defaultBlockState(),
                            3
                    );
                }
            }

            // Keleti fal
            for (int z = 0; z < 16; z++) {
                if (!hasEastDoor(chunkX, chunkZ, z)) {
                    level.setBlock(
                            new BlockPos(startX + 15, y, startZ + z),
                            Blocks.YELLOW_CONCRETE.defaultBlockState(),
                            3
                    );
                }
            }
        }
    }

    private static boolean hasNorthDoor(int x, int z, int position) {
        return position >= 7 && position <= 8 && Math.floorMod(x + z, 3) != 0;
    }

    private static boolean hasSouthDoor(int x, int z, int position) {
        return position >= 7 && position <= 8 && Math.floorMod(x + z + 1, 3) != 0;
    }

    private static boolean hasWestDoor(int x, int z, int position) {
        return position >= 7 && position <= 8 && Math.floorMod(x * 3 + z, 4) != 0;
    }

    private static boolean hasEastDoor(int x, int z, int position) {
        return position >= 7 && position <= 8 && Math.floorMod(x + z * 3, 4) != 0;
    }
}