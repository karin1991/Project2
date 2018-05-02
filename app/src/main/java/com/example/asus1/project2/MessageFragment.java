package com.example.asus1.project2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageFragment extends Fragment {

    private static final String TAG = "MessageFragment";
    private static final String KEY_MESSAGE = "message";

    @BindView(R.id.name)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;

    ChatRow chatMessage;
    MessageDeletedListener listener;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        View layout = view.findViewById(R.id.fragment_message_layout);
//        TextView text = view.findViewById(R.id.fragment_message_text);

        String message = getArguments().getString(ARGS_MESSAGE);
        ButterKnife.bind(this, view);
        title.setText(chatMessage.getSenderName());
        time.setText(DateUtils.getRelativeTimeSpanString(chatMessage.getTime(), System.currentTimeMillis(), DateUtils.FORMAT_ABBREV_ALL));
        content.setText(chatMessage.getContent());


        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MessageDeletedListener) {
            this.listener = (MessageDeletedListener) context;
        }
    }




    public static final String ARGS_MESSAGE = "args_message";

    public static MessageFragment newInstance(ChatRow message)
    {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle();

        bundle.putString(KEY_MESSAGE, message.toString());
        messageFragment.setArguments(bundle);

        return messageFragment;
    }

    @OnClick(R.id.delete)
    public void deleteMessage(){
        if (this.listener != null && chatMessage != null) {
            this.listener.onMessageDeleted(chatMessage);
        }
    }


    public interface MessageDeletedListener {
        void onMessageDeleted(ChatRow msg);
    }

}