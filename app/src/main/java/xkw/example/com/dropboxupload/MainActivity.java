package xkw.example.com.dropboxupload;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dropbox.core.android.Auth;

import xkw.example.com.dropboxupload.dropbox.DropboxClientFactory;
import xkw.example.com.dropboxupload.dropbox.DropboxUploadUtil;

import static xkw.example.com.dropboxupload.MyApplication.getAccessToken;
import static xkw.example.com.dropboxupload.MyApplication.getAppKey;

public class MainActivity extends AppCompatActivity {

    private static final int PICKFILE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.startOAuth2Authentication(MainActivity.this, getAppKey());
            }
        });

        FloatingActionButton upload = (FloatingActionButton)findViewById(R.id.fab_upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFilePicker();
            }
        });

        initPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DropboxClientFactory.init(getAccessToken());
    }

    private void initPermission(){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 2);
    }

    private void launchFilePicker() {
        // Launch intent to pick file for upload
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, PICKFILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICKFILE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // This is the result of a call to launchFilePicker
                DropboxUploadUtil.uploadFile(MainActivity.this, data.getData().toString());
            }
        }
    }
}
