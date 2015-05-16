package com.example.latihan_pmm1;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.EventLogTags.Description;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	final static int CAMERA_RESULT=0;
	ImageView imv;
	String imageFilePath;
	boolean inJustDecodeBounds;
	
	
	Button simpan;
	EditText title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	imageFilePath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/myfavoritepicture.jpg";
		File imageFile=new File(imageFilePath);
		final Uri imageFileUri=Uri.fromFile(imageFile);

		
		
		
		Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
		startActivityForResult(i,CAMERA_RESULT);
		

		
		
	}
	
	protected void onActivityResult(int requestCode,int resultCode,Intent intent){
		super.onActivityResult(requestCode, resultCode,intent);
		
	if(resultCode==RESULT_OK){
			imv=(ImageView)findViewById(R.id.ReturnedImageView);
			Display curentDisplay=getWindowManager().getDefaultDisplay();
			int dw=curentDisplay.getWidth();
			int dh=curentDisplay.getWidth();
			
			BitmapFactory.Options bmpFactoryOptions=new BitmapFactory.Options();
			bmpFactoryOptions.inJustDecodeBounds=true;
			Bitmap bmp=BitmapFactory.decodeFile(imageFilePath,bmpFactoryOptions);
			
			
			
			int heightRatio=(int)Math.ceil(bmpFactoryOptions.outHeight/(float)dh);
			int widthRatio=(int)Math.ceil(bmpFactoryOptions.outWidth/(float)dw);
			
			
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
		
		
		
		imv.setImageBitmap(bmp);
		}
		
	imv.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(inJustDecodeBounds){
				inJustDecodeBounds=false;
				imv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
				imv.setAdjustViewBounds(true);
			}else{
				inJustDecodeBounds=true;
				imv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
				imv.setScaleType(ImageView.ScaleType.FIT_XY);
			}
			
		}
	});
		
		
	}

}
