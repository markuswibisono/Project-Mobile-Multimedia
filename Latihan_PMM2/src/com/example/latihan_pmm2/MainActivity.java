package com.example.latihan_pmm2;

import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static int DISPLAYWIDTH=200;
	public final static int DISPLAYHEIGHT=200;
	TextView titleTextView;
	ImageButton imageButton;
	Cursor cursor;
	Bitmap bmp;
	String imageFilePath;
	int fileColumn;
	int titleColumn;
	int displayColumn;
	boolean inJustDecodeBounds;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		titleTextView=(TextView)findViewById(R.id.TitleTextView);
		imageButton =(ImageButton)findViewById(R.id.ImageButton);
		
		String[]columns={Media.DATA,Media._ID,Media.TITLE,Media.DISPLAY_NAME};
	    cursor=managedQuery(Media.EXTERNAL_CONTENT_URI, columns, null,null,null);
		
	    fileColumn=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    titleColumn=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
	    displayColumn=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
	    if(cursor.moveToFirst()){
	    	titleTextView.setText(cursor.getString(displayColumn));
	    	imageFilePath=cursor.getString(fileColumn);
	    	
	    	bmp=getBitmap(imageFilePath);
			
			imageButton.setImageBitmap(bmp);
	    }
	    imageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cursor.moveToNext()){
					titleTextView.setText(cursor.getString(displayColumn));
					imageFilePath=cursor.getString(fileColumn);
					bmp=getBitmap(imageFilePath);
					imageButton.setImageBitmap(bmp);
				}
				
			}
		});
		
	}
	
private Bitmap getBitmap(String imageFilePath){
	BitmapFactory.Options bmpFactoryOptions=new BitmapFactory.Options();
	bmpFactoryOptions.inJustDecodeBounds=true;
	Bitmap bmp =BitmapFactory.decodeFile(imageFilePath,bmpFactoryOptions);
	
	int heightRatio=(int)Math.ceil(bmpFactoryOptions.outHeight/(float)DISPLAYHEIGHT);
	int widthRatio=(int)Math.ceil(bmpFactoryOptions.outWidth/(float)DISPLAYWIDTH);
	
	Log.v("HeightRatio",""+heightRatio);
	Log.v("WidthRatio",""+widthRatio);
	
	if(heightRatio>1 && widthRatio >1){
		if(heightRatio>widthRatio){
			bmpFactoryOptions.inSampleSize=heightRatio;
		}else{
			bmpFactoryOptions.inSampleSize=widthRatio;
		}
	}
	
	bmpFactoryOptions.inJustDecodeBounds=false;
	bmp=BitmapFactory.decodeFile(imageFilePath,bmpFactoryOptions);
	return bmp;
	
}
	
}
