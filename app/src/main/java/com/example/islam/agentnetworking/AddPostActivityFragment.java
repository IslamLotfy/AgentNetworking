package com.example.islam.agentnetworking;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPostActivityFragment extends Fragment {

    public static final int RESULT_GALLERY = 0;
    private Uri imageUri;
    private EditText title;
    private EditText desc;
    private ImageButton imagebtn;
    private Button postbtn;
    private Post post;
    private User user;
    public AddPostActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_post, container, false);
        imagebtn=(ImageButton)view.findViewById(R.id.imgetbtn);
        title =(EditText)view.findViewById(R.id.posttitle);
        desc = (EditText)view.findViewById(R.id.postdesc);
        postbtn =(Button) view.findViewById(R.id.postbtn);
        post=new Post();
        user=(User) getActivity().getIntent().getSerializableExtra("user");
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operGallery();
            }
        });
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost();
            }
        });
        return view;
    }

    public void addPost() {
        post.setTitle(title.getText().toString());
        post.setDesc(desc.getText().toString());
        post.setUri(imageUri.toString());
        Log.d("post ",post.getTitle());
        Log.d("post ",post.getDesc());
        Log.d("post ",post.getUri().toString());
        Intent intent=new Intent(getContext(),NetworkListActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }

    public void operGallery(){
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent , RESULT_GALLERY );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_GALLERY && resultCode==RESULT_OK){
            imageUri=data.getData();
            System.out.println(imageUri.toString());
            imagebtn.setImageURI(imageUri);
        }
    }
}
