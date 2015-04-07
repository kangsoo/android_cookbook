package com.kangsoo.myapplication;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by bsnc on 2015-04-07.
 */
public class GLExample extends Activity {

    GLSurfaceView ourSurface;

    @Override
    protected void onPause() {
        super.onPause();
        ourSurface.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurface = new GLSurfaceView(this);
        ourSurface.setRenderer(new GLRendererEx());
        setContentView(ourSurface);
    }
}
