package io.uscool.quizapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.uscool.quizapp.R;
import io.uscool.quizapp.models.Subject;

/**
 * Created by ujjawal on 8/6/17.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private List<Subject> mSubjectList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
       void onClick(View view, int position);
    }

    public SubjectAdapter(List<Subject> list, Context context) {
        this.mContext = context;
        this.mSubjectList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subject subject = mSubjectList.get(position);
        holder.subjectName.setText(subject.getName());
        holder.subjectIcon.setImageResource(subject.getIcon_id());
        holder.underline.setBackgroundResource(subject.getUnderline_color_id());

    }

    @Override
    public int getItemCount() {
        return mSubjectList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView subjectIcon;
        TextView subjectName;
        View underline;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectIcon = (ImageView) itemView.findViewById(R.id.subject_image);
            subjectName = (TextView) itemView.findViewById(R.id.subject_name);
            underline = (View) itemView.findViewById(R.id.subject_underline_color);
        }
    }
}
