package com.linkct.hzdlzcgl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by YoKeyword on 16/2/10.
 */
public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<DataInfo> mItems = new ArrayList<>();

    private SparseBooleanArray mBooleanArray;

    private OnItemClickListener mClickListener;

    private int mLastCheckedPosition = -1;

    public LeftMenuAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }


    public void sortNode() {
        sort(mItems);
        notifyDataSetChanged();
    }
    private void sort(List<DataInfo> list) {
        Collections.sort(list);
        for (DataInfo node : list) {
            if (node.getType() == Constant.GROUP) {
                sort(node.getChildren());
            }
        }
    }

    public void setDatas(List<DataInfo> items) {
        mItems.clear();
        mItems.addAll(items);

        mBooleanArray = new SparseBooleanArray(mItems.size());
    }


    // 考虑下步不用递归
    public void addNode(DataInfo root) {
        mItems.add(root);
        if (root != null) {
            for (int x = 0; x < root.getChildren().size(); x++) {
                addNode(root.getChildren().get(x));
            }
        }
    }

    // 类似于删除文件夹和文件的关系，但是有一点，我点击的闭合项是肯定不能删的，所以boolean标记意味着区别于其它
    public void closeGroup(DataInfo node, boolean isRoot) {
        node.setExpanded(false);
        for (int x = 0; x < node.getChildren().size(); x++) {
            DataInfo childNode = node.getChildren().get(x);
            childNode.setExpanded(false);
            if (childNode.getChildren().size() > 0) {
                closeGroup(childNode, false);
            } else {
                mItems.remove(childNode);
            }
        }
        if (node.getType() == Constant.PERSON)
            mItems.remove(node);
        if (!isRoot)
            mItems.remove(node);
    }

    public void controllerItem(DataInfo node) {
        switch (node.getType()) {

            case Constant.GROUP:
                node.setExpanded(!node.isExpanded());
                if (node.isExpanded() == true) {// 如果展开了，就添加它所有的child
                    mItems.addAll(mItems.indexOf(node) + 1, node.getChildren());
                } else {// 反之，则需要从最里层的child进行删除，因为我闭合的有可能是最外层的group
                    closeGroup(node, true);
                }
                this.notifyDataSetChanged();
                break;

            case Constant.PERSON:
                // 开启新的Activity即可
                break;
        }
    }

    public List<DataInfo> getAlls() {
        return mItems;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.data_item_view, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (!mBooleanArray.get(position)) {
            holder.itemView.setBackgroundResource(R.color.white);
            holder.tv_name.setTextColor(Color.BLACK);
        } else {
            holder.itemView.setBackgroundResource(R.color.coloritemSelect);
            holder.tv_name.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        }
        DataInfo node=mItems.get(position);
        if (node.getType()==Constant.GROUP){
            holder.iv_logo.setVisibility(View.GONE);
            holder.tv_name.setText("   "+mItems.get(position).getEquipment_name());
        }else{
            holder.iv_logo.setVisibility(View.VISIBLE);
            holder.tv_name.setText(mItems.get(position).getEquipment_name());
        }

        if (node.isExpanded()){
            holder.iv_logo2.setImageResource(R.drawable.icon_arrowdown_down);
        }else{
            holder.iv_logo2.setImageResource(R.drawable.icon_arrow);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItemChecked(int position) {
        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1&&mLastCheckedPosition!=position) {
            mBooleanArray.put(mLastCheckedPosition, false);
            notifyItemChanged(mLastCheckedPosition);
        }
        notifyDataSetChanged();

        mLastCheckedPosition = position;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_logo;
        ImageView iv_logo2;
        TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            iv_logo2 = (ImageView) itemView.findViewById(R.id.iv_logo2);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
