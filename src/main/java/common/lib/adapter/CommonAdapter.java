package common.lib.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    private OnItemClickListener mOnItemClickListener;

    public CommonAdapter(Context context, int layoutId) {
        mDatas = new ArrayList<>();
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
    }

    public CommonAdapter(Context context, int layoutId, List<T> mDatas) {
        this.mContext = context;
        this.mDatas = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = mDatas;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, null, parent, mLayoutId, -1);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas != null && mDatas.size() > 0 ? mDatas.get(Math.max(0, position - getOtherTypeCount())) : null);
    }

    public abstract void convert(CommonViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas == null || mDatas.size() == 0 ? getOtherTypeCount() : mDatas.size() + getOtherTypeCount();
    }


    protected void setListener(final ViewGroup parent, final CommonViewHolder viewHolder, int viewType) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDatas != null && mDatas.size() > 0 ? mDatas.get(Math.max(0, position - getOtherTypeCount())) : null, position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas != null && mDatas.size() > 0 ? mDatas.get(Math.max(0, position - getOtherTypeCount())) : null, position);
                }
                return true;
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    /**
     * 添加数据列表到列表头部
     */
    public void addListAtStart(List<T> list) {
        this.mDatas.addAll(0, list);
        notifyDataSetChanged();
    }

    public void replaceBean(int position, T e) {
        this.mDatas.remove(position);
        this.mDatas.add(position, e);
        notifyDataSetChanged();
    }

    /**
     * 添加数据列表到列表尾部
     */
    public void addListAtEnd(List<T> list) {
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加单个元素到列表头
     */
    public void addListBeanAtStart(T e) {
        mDatas.add(0, e);
        notifyDataSetChanged();
    }

    /**
     * 添加单个元素到列表头
     */
    public void addBeanAtPosition(T e, int position) {
        mDatas.add(position, e);
        notifyItemInserted(position);
    }

    /**
     * 添加单个元素到列表尾
     */
    public void addListBeanAtEnd(T e) {
        mDatas.add(e);
        notifyDataSetChanged();
    }

    /**
     * 替换ListView数据
     */
    public void replaceList(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    /**
     * 删除ListView所有数据
     */
    public void removeAll() {
        if (mDatas != null) {
            this.mDatas.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 删除ListView指定位置的数据
     */
    public void remove(T e) {
        this.mDatas.remove(e);
        notifyDataSetChanged();
    }

    /**
     * 删除ListView指定位置的数据
     */
    public T remove(int position) {
        if (position >= 0 && position <= mDatas.size() && mDatas != null) {

            T t = this.mDatas.remove(position);
            notifyDataSetChanged();
            return t;
        }
        return null;
    }

    /**
     * 删除ListView指定位置的数据
     */
    public T removeItemRemoved(int position) {
        if (position >= 0 && position <= mDatas.size() && mDatas != null) {

            T t = this.mDatas.remove(position);
            notifyItemRemoved(position);
            return t;
        }
        return null;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> mDatas) {
        if (mDatas == null) {
            this.mDatas.clear();
        } else {
            this.mDatas = mDatas;
        }
    }

    /**
     * 跳转方法
     */
    protected void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    public int getOtherTypeCount() {
        return 0;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup parent, View view, T t, int position);

        boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
    }
}
