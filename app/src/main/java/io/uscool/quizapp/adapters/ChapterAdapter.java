package io.uscool.quizapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.models.Chapter;

/**
 * Created by ujjawal on 9/6/17.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private List<Chapter> mChapterList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
    public ChapterAdapter(List<Chapter> list, Context context) {
        this.mChapterList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChapterAdapter.ViewHolder holder, int position) {
        Chapter chapter = mChapterList.get(position);
        holder.chapterName.setText(chapter.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onClick(view, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChapterList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView chapterName;
        public ViewHolder(View itemView) {
            super(itemView);
            chapterName = (TextView) itemView.findViewById(R.id.chapter_name);
        }
    }
}
