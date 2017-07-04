package common.lib.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import common.lib.R;


/**
 * Created by shangguanruipeng on 2017/7/4.
 */

public class MaterialAlertDialog {

    private AlertDialog.Builder builder = null;
    private AlertDialog alertDialog = null;
    private Context mContext;

    private MaterialAlertDialog(Context context) {
        this.mContext = context;
        builder = new AlertDialog.Builder(mContext);
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", null);
    }


    public static MaterialAlertDialog get(Context context) {
        return new MaterialAlertDialog(context);
    }

    public MaterialAlertDialog setTitle(CharSequence title) {
        builder.setTitle(title);
        return this;
    }

    public MaterialAlertDialog setMessage(CharSequence title) {
        builder.setMessage(title);
        return this;
    }

    public MaterialAlertDialog setMessage(@StringRes int stringId) {
        builder.setMessage(stringId);
        return this;
    }

    public MaterialAlertDialog setButton(CharSequence cancelText, CharSequence sureText, DialogInterface.OnClickListener listener) {
        if (TextUtils.isEmpty(cancelText)) builder.setNegativeButton(cancelText, listener);
        if (TextUtils.isEmpty(sureText)) builder.setPositiveButton(sureText, listener);
        return this;
    }

    public MaterialAlertDialog showDialog() {
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        return this;
    }

    public MaterialAlertDialog showTipsDialog() {
        builder.setNegativeButton(null, null);
        builder.setPositiveButton("知道了", null);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        return this;
    }



    public MaterialAlertDialog setCancelTextColor(@ColorInt int colorRes) {
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(colorRes);
        return this;
    }
    public MaterialAlertDialog setSureTextColor(@ColorInt int colorRes) {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(colorRes);
        return this;
    }

    public MaterialAlertDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

//
//    protected fun showDialog(title: String, message: String, negativButtonMessage: String = "取消", positiveButtonMessage: String = "确定", dialogInterface: DialogInterface.OnClickListener) {
//        if (builder == null) builder = AlertDialog.Builder(this)
//        if (!TextUtils.isEmpty(title)) builder!!.setTitle(title)
//        builder!!.setNegativeButton(negativButtonMessage, dialogInterface)
//        builder!!.setPositiveButton(positiveButtonMessage, dialogInterface)
//        builder!!.setMessage(message)
//        val dialog = builder!!.create()
//        dialog.show()
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GRAY)
//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY)
//        dialog.setCanceledOnTouchOutside(false)
//    }
//
//    protected fun showTipDialog(tips: String) {
//        if (builder == null) builder = AlertDialog.Builder(this)
//        builder!!.setNegativeButton("知道了", null)
//        builder!!.setMessage(tips)
//        val dialog = builder!!.create()
//        dialog.show()
//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.colorPrimary))
//        dialog.setCanceledOnTouchOutside(false)
//    }


}
