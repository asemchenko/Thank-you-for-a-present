package com.example.presentator.modules.addNew;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.presentator.model.entities.Gift;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class PresentAddingController {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    private PresentAddingView view;
    private DatabaseReference giftsRef = FirebaseDatabase.getInstance().getReference().child("gifts");

    private Gift gift;

    public PresentAddingController(PresentAddingView view) {
        this.view = view;
    }

    public void addNewPresent(Gift gift, Bitmap picture) {
        this.gift = gift;
        uploadFile(picture);
    }

    private void uploadFile(Bitmap bitmap) {
        DatabaseReference curGiftPush = giftsRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push();
        StorageReference imageRef = storageRef.child(curGiftPush.getKey() + ".jpg");
        DatabaseReference giftsRef = FirebaseDatabase.getInstance().getReference().child("gifts");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                curGiftPush.removeValue();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        setGiftInfo(task.getResult().toString(), curGiftPush);
                    }
                });
            }
        });
    }

    private void setGiftInfo(String downloadUri, DatabaseReference curGiftPush) {
        gift.setPresentImageURL(downloadUri);
        curGiftPush.setValue(gift);
        view.finishAddingView();
    }
}