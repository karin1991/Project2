package com.example.asus1.project2;




import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {


    final List<ChatRow> chatList;
//    private final OnClickListener listener;

    public void addMessage(ChatRow msg) {
        chatList.add(msg);
        notifyItemInserted(chatList.size() - 1);
    }

    public void removeItem(ChatRow msg) {
        for(int i = 0, size = chatList.size(); i < size; i++) {
            if (msg.equals(chatList.get(i))) {
                chatList.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
        throw new IllegalArgumentException("item is not in dataset");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        TextView name;
        TextView time;
        View.OnClickListener listener;

        public MyViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.content);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
//            view.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (listener != null) {
//                        listener.onClick(chatList.get(getAdapterPosition()));
//                        return true;
//                    }
//                    return false;
//                }
//            });
        }

        void setOnClickListener(View.OnClickListener onClickListener) {
            this.listener = onClickListener;
        }


    }

    interface OnClickListener {
        void onClick(ChatRow message);
    }


    public ChatAdapter(List<ChatRow> chatList)
    {
        this.chatList = chatList;
//        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_list_row, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChatRow row = chatList.get(position);
        holder.content.setText(row.getContent());
        holder.time.setText((DateUtils.getRelativeTimeSpanString(holder.itemView.getContext(), row.getTime()).toString()));

        holder.name.setText(row.getSenderName());

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }







}
