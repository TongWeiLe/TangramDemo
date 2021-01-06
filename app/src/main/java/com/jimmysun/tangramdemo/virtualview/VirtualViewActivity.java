package com.jimmysun.tangramdemo.virtualview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.jimmysun.tangramdemo.R;
import com.jimmysun.tangramdemo.Utils;
import com.tmall.wireless.vaf.framework.VafContext;
import com.tmall.wireless.vaf.framework.ViewManager;
import com.tmall.wireless.vaf.virtualview.Helper.ImageLoader;
import com.tmall.wireless.vaf.virtualview.core.IContainer;
import com.tmall.wireless.vaf.virtualview.event.EventData;
import com.tmall.wireless.vaf.virtualview.event.EventManager;
import com.tmall.wireless.vaf.virtualview.event.IEventProcessor;
import com.tmall.wireless.vaf.virtualview.view.image.ImageBase;

import org.json.JSONObject;

public class VirtualViewActivity extends AppCompatActivity {
    private static final String TAG = "VirtualViewActivity";

    private LinearLayout mLinearLayout;

    private LinearLayout mTopLayout;
    private LinearLayout mBottomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_view);
        mLinearLayout = findViewById(R.id.layout);
        mTopLayout = findViewById(R.id.home_assert_top_layout);
        mBottomLayout = findViewById(R.id.home_assert_bottom_layout);
        initVirtualView();
    }

    private void initVirtualView() {
        VafContext vafContext = new VafContext(getApplicationContext());
        vafContext.setImageLoaderAdapter(new ImageLoader.IImageLoaderAdapter() {
            @Override
            public void bindImage(String uri, ImageBase imageBase, int reqWidth, int reqHeight) {
                if (Utils.isValidContextForGlide(VirtualViewActivity.this)) {
                    RequestBuilder requestBuilder =
                            Glide.with(VirtualViewActivity.this).asBitmap().load(uri);
                    if (reqWidth > 0 || reqHeight > 0) {
                        requestBuilder.submit(reqWidth, reqHeight);
                    }
                    ImageTarget imageTarget = new ImageTarget(imageBase);
                    requestBuilder.into(imageTarget);
                }
            }

            @Override
            public void getBitmap(String uri, int reqWidth, int reqHeight,
                                  ImageLoader.Listener lis) {
                if (Utils.isValidContextForGlide(VirtualViewActivity.this)) {
                    RequestBuilder requestBuilder =
                            Glide.with(VirtualViewActivity.this).asBitmap().load(uri);
                    if (reqWidth > 0 || reqHeight > 0) {
                        requestBuilder.submit(reqWidth, reqHeight);
                    }
                    ImageTarget imageTarget = new ImageTarget(lis);
                    requestBuilder.into(imageTarget);
                }
            }
        });
        ViewManager viewManager = vafContext.getViewManager();
        viewManager.init(getApplicationContext());
//        viewManager.loadBinBufferSync(VVTEST.BIN);
//        viewManager.loadBinFileSync("file:///android_asset/VVTest.out");
        viewManager.loadBinBufferSync(Utils.getAssetsFile(this, "ViewPager.out"));
        viewManager.loadBinBufferSync(Utils.getAssetsFile(this,"BusinessInfo.out"));
        vafContext.getEventManager().register(EventManager.TYPE_Click, new IEventProcessor() {
            @Override
            public boolean process(EventData data) {
                Toast.makeText(VirtualViewActivity.this, data.mVB.getAction(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        vafContext.getEventManager().register(EventManager.TYPE_Exposure, new IEventProcessor() {
            @Override
            public boolean process(EventData data) {
                Log.d(TAG, "Exposure process: " + data.mVB.getViewCache().getComponentData());
                return true;
            }
        });
        // ViewPager
//        container = vafContext.getContainerService().getContainer("ViewPager", true);
//        mLinearLayout.addView(container);
//        iContainer = (IContainer) container;
//        jsonObject = Utils.getJSONDataFromAsset(this, "view_pager.json");
//        if (jsonObject != null) {
//            iContainer.getVirtualView().setVData(jsonObject);
//        }

        View businessView = vafContext.getContainerService().getContainer("BusinessInfo", true);
        View businessView2 = vafContext.getContainerService().getContainer("BusinessInfo", true);
        View businessView3 = vafContext.getContainerService().getContainer("BusinessInfo", true);
        View businessView4 = vafContext.getContainerService().getContainer("BusinessInfo", true);

        mTopLayout.addView(businessView);
        mTopLayout.addView(businessView2);
        mBottomLayout.addView(businessView3);
        mBottomLayout.addView(businessView4);


        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) businessView.getLayoutParams();
        params.weight =1;
        params.width = 0;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        businessView.setLayoutParams(params);
        businessView2.setLayoutParams(params);
        businessView3.setLayoutParams(params);
        businessView4.setLayoutParams(params);
        IContainer iContainer = (IContainer) businessView;
        IContainer iContainer2 = (IContainer) businessView2;
        IContainer iContainer3 = (IContainer) businessView3;
        IContainer iContainer4 = (IContainer) businessView4;
        JSONObject jsonObject = Utils.getJSONDataFromAsset(this, "business.json");
        JSONObject jsonObject2 = Utils.getJSONDataFromAsset(this, "business2.json");
        JSONObject jsonObject3 = Utils.getJSONDataFromAsset(this, "business3.json");
        JSONObject jsonObject4 = Utils.getJSONDataFromAsset(this, "business4.json");
        if (jsonObject != null) {
            iContainer.getVirtualView().setVData(jsonObject);
        }
        if (jsonObject2 != null) {
            iContainer2.getVirtualView().setVData(jsonObject2);
        }
        if (jsonObject3 != null) {
            iContainer3.getVirtualView().setVData(jsonObject3);
        }
        if (jsonObject4 != null) {
            iContainer4.getVirtualView().setVData(jsonObject4);
        }

    }
}
