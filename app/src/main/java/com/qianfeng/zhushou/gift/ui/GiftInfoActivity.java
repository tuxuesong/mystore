package com.qianfeng.zhushou.gift.ui;

import android.text.Html;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.gift.bean.GiftInfo;
import com.qianfeng.zhushou.gift.util.GiftHttpUtil;
import com.qianfeng.zhushou.other.ui.BaseActivity;
import com.qianfeng.zhushou.other.utils.ImageLoader;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouContants;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.other.widget.CircleImageView;
import com.qianfeng.zhushou.other.widget.SharePopWindow;

import org.json.JSONException;
import org.json.JSONObject;

public class GiftInfoActivity extends BaseActivity
{

    private ImageView ivAndroid, ivIos;

    private CircleImageView ivHeader;

    private TextView tvName, tvValidity, tvUbi, tvContentValue, tvGetValue,
            tvGameType, tvSize, tvPer;

    private ProgressBar progressBar;

    private Button btnDownLoad;

    private View giftTypeGroup;

    @Override
    protected int getLayout()
    {
        return R.layout.activity_gift_info;
    }

    @Override
    protected void initViews()
    {
        setTitleText(R.string.gift_info_title);
        showLeftImage();
        setRightImage(R.drawable.selector_share);

        ivHeader = (CircleImageView) findViewById(R.id.gift_info_head_iv);
        ivAndroid = (ImageView) findViewById(R.id.gift_info_android_iv);
        ivIos = (ImageView) findViewById(R.id.gift_info_ios_iv);
        tvName = (TextView) findViewById(R.id.gift_info_name_tv);
        tvUbi = (TextView) findViewById(R.id.gift_info_ubi_tv);
        tvValidity = (TextView) findViewById(R.id.gift_info_validity_tv);
        tvContentValue = (TextView) findViewById(R.id.gift_info_content_value_tv);
        tvGetValue = (TextView) findViewById(R.id.gift_info_get_value_tv);
        tvGameType = (TextView) findViewById(R.id.gift_info_game_type_tv);
        tvSize = (TextView) findViewById(R.id.gift_info_size_tv);
        tvPer = (TextView) findViewById(R.id.gift_info_per_tv);
        progressBar = (ProgressBar) findViewById(R.id.gift_info_pb);

        btnDownLoad = (Button) findViewById(R.id.gift_info_down_btn);
        giftTypeGroup = findViewById(R.id.gift_info_type_rl);
    }

    @Override
    protected void initEvents()
    {
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示分享菜单

                SharePopWindow popWindow = new SharePopWindow(
                        GiftInfoActivity.this);
                // popWindow.showAsDropDown(ivHeader);
                popWindow.showAtLocation(llContent, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

    }

    @Override
    protected void initData()
    {
        // 获取从上一页传进来的id
        String id = getIntent().getStringExtra("id");

        GiftHttpUtil.requestGiftInfo(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject
                            .getString(ZhuShouContants.FLAG_REQUEST_STATE);
                    // 如果请求成功了，接下来作数据解析
                    if (ZhuShouContants.FLAG_REQUEST_SUCCESS.equals(state)) {
                        JSONObject info = jsonObject.getJSONObject("info");

                        GiftInfo giftInfo = GiftInfo.objectFromData(info
                                .toString());

                        LogUtil.d("tag", "giftInfo = " + giftInfo);

                        ImageLoader imageLoader = new ImageLoader(
                                GiftInfoActivity.this);

                        // imageLoader.DisplayImage(giftInfo.getIcon(),
                        // ivHeader);
                        String url = giftInfo.getIcon();
                        ivHeader.setImageUrl(url);

                        tvName.setText(giftInfo.getName());

                        String ubi = getResources().getString(
                                R.string.gift_info_consume,
                                giftInfo.getConsume());
                        tvUbi.setText(ubi);

                        // 设置剩余百分比
                        String per = getString(R.string.gift_info_per,
                                giftInfo.getRemain(), giftInfo.getTotal());
                        tvPer.setText(per);

                        int remain = giftInfo.getRemain();

                        int total = 0;
                        try
                        {
                            total = Integer.parseInt(giftInfo.getTotal());
                            progressBar.setMax(total);
                            progressBar.setProgress(remain);
                        }
                        catch (NumberFormatException e)
                        {
                            e.printStackTrace();
                        }

                        // 设置有效期
                        String validity = getString(
                                R.string.gift_info_validity,
                                giftInfo.getStime(), giftInfo.getEtime());
                        tvValidity.setText(validity);

                        // 适用于哪种平台?
                        String platform = giftInfo.getPlatform();
                        // 如果＝＝1表示适用于android
                        if ("1".equals(platform)) {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.INVISIBLE);
                        }
                        // 如果＝＝2表示适用于ios
                        else if ("2".equals(platform)) {
                            ivAndroid.setVisibility(View.INVISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }
                        // 其他情况是适用于android和ios
                        else {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }

                        // 如果游戏类型或者游戏大小为"null"，那么不显示下载这一栏
                        String gameType = giftInfo.getGame_type();
                        String gameSize = giftInfo.getSize();
                        if (!(gameType == null || gameSize == null)) {
                            giftTypeGroup.setVisibility(View.VISIBLE);
                            // 设置游戏类型
                            gameType = getString(R.string.gift_info_type,
                                    gameType);
                            tvGameType.setText(gameType);

                            gameSize = getString(R.string.gift_info_size,
                                    gameSize);
                            tvSize.setText(gameSize);
                        }

                        // 去掉字符串中的html标签
                        Spanned content = Html.fromHtml(giftInfo.getContent());
                        tvContentValue.setText(content.toString());
                        Spanned get = Html.fromHtml(giftInfo.getHowget());
                        tvGetValue.setText(get.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {

            }
        });

    }

}
