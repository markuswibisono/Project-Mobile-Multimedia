package com.example.belajaropengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Canvas.VertexMode;
import android.opengl.GLSurfaceView.Renderer;

public class openGlRender implements Renderer {

	private ShortBuffer _indexBuffer;
	private FloatBuffer _vertexBuffer;
	private ShortBuffer _indexBuffer1;
	private FloatBuffer _vertexBuffer1;

	private ShortBuffer _indexBuffer2;
	private FloatBuffer _vertexBuffer2;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	  
		iniTriangle();
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClearColor(1f,1f,1f, 1f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glColor4f(0.5f,0f,0f,0.5f);

		
		gl.glVertexPointer(2,GL10.GL_FLOAT,0,_vertexBuffer);
		gl.glDrawElements(GL10.GL_LINES,2,GL10.GL_UNSIGNED_SHORT,_indexBuffer);
		

		gl.glVertexPointer(2,GL10.GL_FLOAT,0,_vertexBuffer1);
		gl.glDrawElements(GL10.GL_LINES,2,GL10.GL_UNSIGNED_SHORT,_indexBuffer1);
	
		gl.glVertexPointer(2,GL10.GL_FLOAT,0,_vertexBuffer2);
		gl.glDrawElements(GL10.GL_LINES,2,GL10.GL_UNSIGNED_SHORT,_indexBuffer2);
	}
	
	
	
private void iniTriangle(){
	int _nrOfVertices=3;
	
	ByteBuffer vbb= ByteBuffer.allocateDirect(_nrOfVertices * 3 * 4);
	vbb.order(ByteOrder.nativeOrder());
	_vertexBuffer=vbb.asFloatBuffer();
	
	
	ByteBuffer ibb=ByteBuffer.allocateDirect(_nrOfVertices *2);
	ibb.order(ByteOrder.nativeOrder());
	_indexBuffer=ibb.asShortBuffer();
	
	
	ByteBuffer vbb1= ByteBuffer.allocateDirect(_nrOfVertices * 3 * 4);
	vbb1.order(ByteOrder.nativeOrder());
	_vertexBuffer1=vbb1.asFloatBuffer();
	
	
	ByteBuffer ibb1=ByteBuffer.allocateDirect(_nrOfVertices *2);
	ibb1.order(ByteOrder.nativeOrder());
	_indexBuffer1=ibb1.asShortBuffer();

	
	
	ByteBuffer vbb2= ByteBuffer.allocateDirect(_nrOfVertices * 3 * 4);
	vbb2.order(ByteOrder.nativeOrder());
	_vertexBuffer2=vbb2.asFloatBuffer();
	
	
	ByteBuffer ibb2=ByteBuffer.allocateDirect(_nrOfVertices *2);
	ibb2.order(ByteOrder.nativeOrder());
	_indexBuffer2=ibb2.asShortBuffer();
	
	
	float[]coords={0.5f,0.0f
	             

	};//garis datar
	short []_indicesArray={ 0,1};
	

	_vertexBuffer.put(coords);
	_indexBuffer.put(_indicesArray);
	_vertexBuffer.position(0);
	_indexBuffer.position(0);
	
	float[]coords2={0.5f,5.5f
            

	};//garis datar
	short []_indicesArray2={ 0,1};
	

	_vertexBuffer2.put(coords2);
	_indexBuffer2.put(_indicesArray2);
	_vertexBuffer2.position(0);
	_indexBuffer2.position(0);
	
	
	float[]coords1={0.0f,0.5f
            

	};//garis datar
	short []_indicesArray1={ 0,1};
	

	_vertexBuffer1.put(coords1);
	_indexBuffer1.put(_indicesArray1);
	_vertexBuffer1.position(0);
	_indexBuffer1.position(0);
	
	
	
	
	
	
	
	
}
	
	
}
