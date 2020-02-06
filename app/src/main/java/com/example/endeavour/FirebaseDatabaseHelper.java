package com.example.endeavour;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
     private DatabaseReference mRefrenceBooks;
     private List<Book> books= new ArrayList<>(  );

     public interface DataStatus{

         void DataIsInserted();






    }


     public FirebaseDatabaseHelper(){

         mDatabase=FirebaseDatabase.getInstance();
         mRefrenceBooks=mDatabase.getReference("contact_us");       //books
     }


     public void addBook(Book book , final DataStatus dataStatus){
       String key = mRefrenceBooks.push().getKey();
       mRefrenceBooks.child( key ).setValue( book )
               .addOnSuccessListener( new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       dataStatus.DataIsInserted();
                   }
               } );


     }
}
