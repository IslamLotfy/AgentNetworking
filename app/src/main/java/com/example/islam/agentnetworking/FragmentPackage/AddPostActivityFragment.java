package com.example.islam.agentnetworking.FragmentPackage;

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
import android.widget.Toast;

import com.example.islam.agentnetworking.ActivityPackage.NetworkListActivity;
import com.example.islam.agentnetworking.CallBackPackage.AddPostListener;
import com.example.islam.agentnetworking.FirebaseHelpers.DatabaseHelper;
import com.example.islam.agentnetworking.ModelsPackage.PostModel;
import com.example.islam.agentnetworking.Presenters.AddPostPresenter;
import com.example.islam.agentnetworking.R;
import com.example.islam.agentnetworking.ModelsPackage.UserModel;

import java.io.Serializable;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddPostActivityFragment extends Fragment {

    public static final int RESULT_GALLERY = 0;
    private Uri imageUri;
    private EditText title;
    private EditText desc;
    private ImageButton imageButton;
    private Button postButton;
    private AddPostPresenter presenter;

    public AddPostActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_post, container, false);
        imageButton =(ImageButton)view.findViewById(R.id.imgetbtn);
        title =(EditText)view.findViewById(R.id.posttitle);
        desc = (EditText)view.findViewById(R.id.postdesc);
        postButton =(Button) view.findViewById(R.id.postbtn);
        presenter=new AddPostPresenter();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPost();
            }
        });
        return view;
    }

    public void addPost() {
        String titleTxt=title.getText().toString();
        String descTxt=desc.getText().toString();
        String postId=UUID.randomUUID().toString();
        String imgUri=imageUri.toString();
        presenter.AddPost(titleTxt, descTxt, imgUri, postId, new AddPostListener() {
            @Override
            public void onSuccess(PostModel postModel) {
                Intent intent=new Intent(getActivity(),NetworkListActivity.class);
                intent.putExtra("post", postModel);
                startActivity(intent);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(),"an error occured \n please check the fields !",Toast.LENGTH_SHORT);
            }
        });

    }

    public void openGallery(){
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
            imageButton.setImageURI(imageUri);
        }
    }
}
