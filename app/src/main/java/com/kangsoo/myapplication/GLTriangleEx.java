package com.kangsoo.myapplication;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by bsnc on 2015-04-07.
 */
public class GLTriangleEx {

    private float vertices[] = {
        0f, 1f,     //p0
        1f, -1f,    //p1
        -1f, -1f    //p2
    };

    private FloatBuffer vertBuff;
    private Short[] pIndex = { 0, 1, 2};
    private ShortBuffer pBuff;

    public GLTriangleEx() {

        ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuff = bBuff.asFloatBuffer();
        vertBuff.put(vertices);
        vertBuff.position(0);

        ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length);
        pbBuff.order(ByteOrder.nativeOrder());
        pBuff = pbBuff.asShortBuffer();
        pBuff.put(pIndex[0]);
        pBuff.position(0);
    }

    public void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,vertBuff);
        gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
