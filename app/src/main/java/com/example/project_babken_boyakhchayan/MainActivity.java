package com.example.project_babken_boyakhchayan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity  {

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
//        Intent intent = new Intent(MainActivity.this,WallpaperActivity.class);
//        startActivity(intent);
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this,WallpaperActivity.class);
                    switch (finalI){
                        case 0:
                            intent.putExtra("CATEGORY","animal");
                            break;
                        case 1:
                            intent.putExtra("CATEGORY","flower");
                            break;
                        case 2:
                            intent.putExtra("CATEGORY","human");
                            break;
                        case 3:
                            intent.putExtra("CATEGORY","car");
                            break;
                        case 4:
                            intent.putExtra("CATEGORY","mountain");
                            break;
                        case 5:
                            intent.putExtra("CATEGORY","nature");
                            break;
                        case 6:
                            intent.putExtra("CATEGORY","tree");
                            break;
                        case 7:
                            intent.putExtra("CATEGORY","water");
                            break;
                        case 8:
                            intent.putExtra("CATEGORY","book");
                            break;
                        case 9:
                            intent.putExtra("CATEGORY","music");
                            break;
                    }
                    startActivity(intent);
                }
            });
        }
    }
}
