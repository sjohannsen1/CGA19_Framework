package CGA.User.DataStructures;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;
import CGA.Framework.*;

import static CGA.Framework.GLError.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by Fabian on 16.09.2017.
 */
public class Texture2D implements ITexture{
    private int texID;

    public Texture2D(String path, boolean genMipMaps) {
        IntBuffer x = BufferUtils.createIntBuffer(1);
        IntBuffer y = BufferUtils.createIntBuffer(1);
        IntBuffer readChannels = BufferUtils.createIntBuffer(1);
        //flip y coordinate to make OpenGL happy
        stbi_set_flip_vertically_on_load(true);
        ByteBuffer imageData = stbi_load(path, x, y, readChannels, 4);

        try {
            if (imageData == null)
                throw new Exception("Image file \"" + path + "\" couldn't be read:\n" + stbi_failure_reason());
            processTexture(imageData, x.get(), y.get(), genMipMaps);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (imageData != null)
                stbi_image_free(imageData);
        }
    }

    public Texture2D(ByteBuffer data, int width, int height, boolean genMipMaps) {
        try {
            processTexture(data, width, height, genMipMaps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void processTexture(ByteBuffer imageData, int width, int height, boolean genMipMaps) throws Exception {
        //TODO: Place your code here
    }

    public void setTexParams(int wrapS, int wrapT, int minFilter, int magFilter) throws Exception {

        //TODO: Place your code here

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, magFilter);



    }

    public void bind(int textureUnit) {
        //TODO: Place your code here
    }

    public void unbind() {
        //TODO: Place your code here
    }

    public int getTexID() {
        //TODO: Place your code here
    }

    public void cleanup() {
        unbind();
        if (texID != 0) {
            glDeleteTextures(texID);
            texID = 0;
        }
    }
}