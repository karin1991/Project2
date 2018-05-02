package com.example.asus1.project2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.design.widget.Snackbar;



public class MainActivity extends AppCompatActivity implements ChatAdapter.OnClickListener, MessageFragment.MessageDeletedListener {

    private static final String TAG = "MainActivity";
    private static final String MESSAGES = "messages";

    private List<ChatRow> chatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChatAdapter mAdapter;
    private Button senderButton;
    private EditText input;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        ArrayList<String> messages = new ArrayList<>(mAdapter.chatList.size());
        for (ChatRow msg : mAdapter.chatList) {
            messages.add(msg.toString());
        }
        outState.putStringArrayList(MESSAGES, messages);

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = findViewById(R.id.recycler_view);

        mAdapter = new ChatAdapter(chatList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        senderButton = findViewById(R.id.sendButton);
        input = findViewById(R.id.editText);




        senderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(input.getText())) {
                    Snackbar.make(input, "must enter a message to send", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                ChatRow msg = new ChatRow("Karin", input.getText().toString(), System.currentTimeMillis());
                mAdapter.addMessage(msg);
                input.setText("");
            }
        });

        input = findViewById(R.id.editText);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }

            @Override
            public void afterTextChanged(Editable s) {
                senderButton.setEnabled(!TextUtils.isEmpty(s));
            }
        });



    }


    @Override
    public void onClick(ChatRow message) {
        MessageFragment frag = MessageFragment.newInstance(message);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_frame, frag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onMessageDeleted(ChatRow msg) {
        mAdapter.removeItem(msg);
        getSupportFragmentManager().popBackStack();
    }
}







