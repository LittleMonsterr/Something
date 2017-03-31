package weiyu.com.vrdemo;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.google.vr.vrcore.logging.api.VREventParcelable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


	private VrPanoramaView vr_pano_view;
	private String TAG = "VrPanoramaView_mainacticity";
	private VrVideoView vr_video_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vr_pano_view = (VrPanoramaView) findViewById(R.id.vr_pano_view);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bike);
		vr_pano_view.loadImageFromBitmap(bitmap,null);

		vr_video_view = (VrVideoView) findViewById(R.id.vr_vidoe_view);

		VrVideoView.Options options = new VrVideoView.Options();
		options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
		try {
			vr_video_view.loadVideoFromAsset("cango.mp4",null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
