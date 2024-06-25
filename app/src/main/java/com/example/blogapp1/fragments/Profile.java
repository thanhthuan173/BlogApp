package com.example.blogapp1.fragments;

import static com.bumptech.glide.Glide.init;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.blogapp1.R;
//import com.example.blogapp1.model.PostimageModel;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Document;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends Fragment {

    private TextView nameTv, toolbarnameTv, followingCountTv, PostCountTv;
    private CircleImageView profileImage;
    private Button followBtn;
    private RecyclerView recyclerView;
    private FirebaseUser user;
    private ImageButton editProfileBtn;
    private TextView statusTv;
    private TextView followersCountTv;
    private View postCountTv;
    private LinearLayout countLayout;

    boolean isMyProfile = true;
    String uid;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        init(view);
        if(isMyProfile){
            followBtn.setVisibility(View.GONE);
            countLayout.setVisibility(View.VISIBLE);
        } else {
            followBtn.setVisibility(View.VISIBLE);
            countLayout.setVisibility(View.GONE);
        }
//        loadBasicData();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

//        loadPostImage();

    }

    private void init(View view) {
        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.toolbar);
        assert getActivity() != null;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        nameTv = view.findViewById(R.id.nameTv);
//        statusTv = view.findViewById(R.id.statusTv);
        toolbarnameTv = view.findViewById(R.id.toolbarnameTv);
        followersCountTv = view.findViewById(R.id.followersCountTv);
        followingCountTv = view.findViewById(R.id.followingCountTv);
        postCountTv = view.findViewById(R.id.postCountTv);
        profileImage = view.findViewById(R.id.profileImage);
//        followBtn = view.findViewById(R.id.followBtn);
        recyclerView = view.findViewById(R.id.recyclerView);
        countLayout = view.findViewById(R.id.countLayout);
        editProfileBtn = view.findViewById(R.id.edit_profileImage);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


    }

//    private void loadBasicData() {
//
//        DocumentReference userRef = FirebaseFirestore.getInstance().collection("User")
//                .document(user.getUid());
//        userRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                if (error != null) {
//
//                    return;
//
//                    if (value.exists()) {
//
//
//                        String name = value.getString("name");
//                        String status = value.getString("status");
//                        int followers = value.getLong("followers").intValue();
//                        int following = value.getLong("following").intValue();
//
//                        String profileURL = value.getString("profileimage");
//
//                        nameTv.setText(name);
//                        toolbarnameTv.setText(name);
//                        statusTv.setText(status);
//                        followersCountTv.setText(String.valueOf(followers));
//                        followingCountTv.setText(String.valueOf(following));
//
//                        Glide.with(getContext().getApplicationContext())
//                                .load(profileURL)
//                                .placeholder(R.drawable.ic_person)
//                                .timeout(6500)
//                                .into(profileImage);
//
//                    }
//                }
//            }
//        });
//    }

//    private  void loadPostImage() {
//
//        if (isMyProfile){
//            uid = user.getUid();
//        }else {
//
//        }
//
//        DocumentReference reference = FirebaseFirestore.getInstance().collection("Users").document(uid);
//
//        Query query = reference.collection("Images");
//
//        FirestoreRecyclerOptions<PostimageModel> options = new FirestoreRecyclerOptions.Builder<PostimageModel>
//                .setQuery(query, PostimageModel.class)
//                .build();
//
//        adapter = new FirestoreRecyclerAdapter<PostimageModel, PostimageHolder>(options);
//        @NonNull
//        @Override
//        public PostimageModel onCreateViewHolder(@NonNull ViewGroup parent, int viewtype){
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_image_items, parent, false);
//            return new PostImageHolder(view) ;
//        }
//        @Override
//        protected void onBindViewHolder(@NonNull PostImageHolder holder, int position, @NonNull PostimageModel model){
//
//            Glide.with(holder.itemView.getcontext().getApplicationContext())
//                    .load(model.getImageURL())
//                    .timeout(6500)
//                    .into(holder.imageView);
//
//        };
//    }
    private static class PostImageHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        public PostImageHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
        }

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}