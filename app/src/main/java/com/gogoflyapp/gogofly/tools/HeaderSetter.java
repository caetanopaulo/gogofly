package com.gogoflyapp.gogofly.tools;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.gogoflyapp.gogofly.Activities.FlightsOverviewActivity;
import com.gogoflyapp.gogofly.R;

/**
 * Created by Kees Marijs on 12/10/2016.
 *
 * Class that links the header to functions.
 */

public class HeaderSetter {

    private Context context;
    private View view;

    /**
     * With this constructor we have the stuff we need to add all the functions to it.
     * @param context                       Needed for resources
     * @param header_view                   The View that contains what we need.
     */
    public HeaderSetter(Context context, View header_view) {
        this.context = context;
        this.view = header_view;
    }

    public void settleIn() {
        ImageView imageViewUser = (ImageView) view.findViewById(R.id.imageView_user);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FlightsOverviewActivity.class);
                //startActivity(intent);
            }
        });
    }
}
