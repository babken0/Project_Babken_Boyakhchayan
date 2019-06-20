package com.example.project_babken_boyakhchayan;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class WallpaperActivity extends AppCompatActivity {


    private ProgressDialog pd;
    private Retrofit retrofit;
    private CompositeDisposable compositeDisposable;
    private String categoryName;
    private ImageAdapter adapter;
    private RecyclerView recyclerView;
    List<String> urls;

    ImageView iv;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        textView = findViewById(R.id.categoryName);
        recyclerView = findViewById(R.id.recyclerViewImage);

        Intent intent  = getIntent();
        categoryName = intent.getStringExtra("CATEGORY");
        textView.setText(categoryName);

        urls = new ArrayList<>();

        pd = new ProgressDialog(this);
        pd.setTitle("wait");
        pd.setMessage("Load");

        compositeDisposable = new CompositeDisposable();

        int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext(),125);
        GridLayoutManager manager = new GridLayoutManager(this,mNoOfColumns);
        recyclerView.setLayoutManager(manager);

        adapter = new ImageAdapter(urls,this);
        recyclerView.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WallpaperService service = retrofit.create(WallpaperService.class);

        service.getCategory(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoryWallpaperList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        pd.show();
                    }

                    @Override
                    public void onSuccess(CategoryWallpaperList categoryWallpaperList) {
                        for (int i = 0; i < 18;i++){
                            urls.add(0,categoryWallpaperList.getHits()[i].getWebformatURL());
                            adapter.notifyItemInserted(0);
                        }
                        //Toast.makeText(WallpaperActivity.this, ""+urls.size(), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(WallpaperActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(WallpaperActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d("Error", " onError : " + e.getMessage());
                        pd.dismiss();
                    }
                });

            }


    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
