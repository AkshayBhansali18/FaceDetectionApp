package com.example.mlkitproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button camera_button;
    ImageView imageView;
    public static int Request_capture=123;
    FirebaseVisionImage image;
    FirebaseVisionFaceDetector detector;
    String resultText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        camera_button=(Button)findViewById(R.id.camera_button);
        imageView=(ImageView)findViewById(R.id.imageView);
        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(pictureIntent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(pictureIntent,Request_capture);
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, @Nullable Intent data) {
        if(requestCode==Request_capture&&resultCode==RESULT_OK)
        {
            final Bundle bundle=data.getExtras();
            Bitmap bitmap= (Bitmap) bundle.get("data");
            FirebaseVisionFaceDetectorOptions highAccuracyOpts =
                    new FirebaseVisionFaceDetectorOptions.Builder()
                            .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE).
                            setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                            .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS).
                            setMinFaceSize(0.14f)
                            .build();
            try {
                image=FirebaseVisionImage.fromBitmap(bitmap);
                detector=FirebaseVision.getInstance().getVisionFaceDetector(highAccuracyOpts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                @Override
                public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                    int i=0;
                    for(FirebaseVisionFace face:firebaseVisionFaces)
                    {
                        resultText+="Smile Percentage: "+(face.getSmilingProbability()*100)+"%\n";
                        resultText+="Left eye open Percentage: "+(face.getLeftEyeOpenProbability()*100)+"%\n";
                        resultText+="Right eye open Percentage: "+(face.getRightEyeOpenProbability()*100)+"%\n";
face.
                    }
                    Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                    intent.putExtra("details",resultText);
                    resultText="";

                    startActivity(intent);




        }
    });
        }
    }
}
