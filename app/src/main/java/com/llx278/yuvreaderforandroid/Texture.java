package com.llx278.yuvreaderforandroid;

import android.opengl.GLES30;

import java.nio.ByteBuffer;

public class Texture implements ITexture {

    private int width;
    private int height;
    private int textureId;
    private ByteBuffer buffer;

    public Texture(ByteBuffer data, int width, int height) {
        this.width = width;
        this.height = height;
        this.buffer = data;
        initTexture();
    }

    private void initTexture() {
        int[] textures = new int[1];
        GLES30.glGenTextures(
                1, // 产生纹理Id的数量
                textures, // 纹理id数组
                0); // 偏移

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textures[0]);
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_MIRRORED_REPEAT);
        GLES30.glTexParameterf(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_MIRRORED_REPEAT);
        GLES30.glTexImage2D(GLES30.GL_TEXTURE_2D, 0, GLES30.GL_RGBA, this.width, this.height,
                0, GLES30.GL_RGBA, GLES30.GL_UNSIGNED_BYTE, buffer);
        this.textureId = textures[0];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getTextureId() {
        return this.textureId;
    }

}
