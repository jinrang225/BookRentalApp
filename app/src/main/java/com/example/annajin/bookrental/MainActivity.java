package com.example.annajin.bookrental;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity implements View.OnClickListener {

    Button buttonCreate, buttonEditCondition, buttonEditBorrower, buttonDelete, buttonCheck;
    EditText editTextCreateTitle, editTextCreateAuthor, editTextCreateCondition, editTextCreateBorrower;
    EditText editTextEditCondition, editTextEditBorrower;
    EditText editTextEditTitle_Condition, editTextEditTitle_Borrower;
    EditText editTextDeleteTitle, editTextFindFriend;
    TextView textViewShowBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreate = findViewById(R.id.buttonCreate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonEditCondition = findViewById(R.id.buttonEditCondition);
        buttonEditBorrower = findViewById(R.id.buttonEditBorrower);
        buttonCheck = findViewById(R.id.buttonCheck);

        editTextCreateAuthor = findViewById(R.id.editTextCreateAuthor);
        editTextCreateTitle = findViewById(R.id.editTextCreateTitle);
        editTextCreateCondition = findViewById(R.id.editTextCreateCondition);
        editTextCreateBorrower = findViewById(R.id.editTextCreateBorrower);
        editTextEditTitle_Condition = findViewById(R.id.editTextEditTitle_Condition);
        editTextEditTitle_Borrower = findViewById(R.id.editTextEditTitle_Borrower);
        editTextEditCondition = findViewById(R.id.editTextEditCondition);
        editTextEditBorrower = findViewById(R.id.editTextEditBorrower);
        editTextDeleteTitle = findViewById(R.id.editTextDeleteTitle);
        editTextFindFriend = findViewById(R.id.editTextFindFriend);

        textViewShowBooks = findViewById(R.id.textViewShowBooks);

        buttonCreate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonEditCondition.setOnClickListener(this);
        ;
        buttonEditBorrower.setOnClickListener(this);
        ;
        buttonCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("books");

        //myRef.setValue("testing");

        if (v == buttonCreate) {

            String createTitle = editTextCreateTitle.getText().toString();
            String createAuthor = editTextCreateAuthor.getText().toString();
            String createCondition = editTextCreateCondition.getText().toString();
            String createBorrower = editTextCreateBorrower.getText().toString();

            Book newBook = new Book(createAuthor, createTitle, createCondition, createBorrower);

            myRef.push().setValue(newBook);

        }
        if (v == buttonDelete) {
            String deleteTitle = editTextDeleteTitle.getText().toString();

            myRef.orderByChild("author").equalTo(deleteTitle).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String deleteKey = dataSnapshot.getKey();
                    myRef.child(deleteKey).setValue(null);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (v == buttonEditCondition) {
            String editTitle_Condition = editTextEditTitle_Condition.getText().toString();
            final String editCondition = editTextEditCondition.getText().toString();

            myRef.orderByChild("title").equalTo(editTitle_Condition).addChildEventListener(new ChildEventListener() {


                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String editKey = dataSnapshot.getKey();
                    myRef.child(editKey).child("condition").setValue(editCondition);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if (v == buttonEditBorrower) {
                String editTitle_Borrower = editTextEditTitle_Borrower.getText().toString();
                final String editBorrower = editTextEditBorrower.getText().toString();

                myRef.orderByChild("title").equalTo(editTitle_Borrower).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String editKey = dataSnapshot.getKey();
                        myRef.child(editKey).child("borrower").setValue(editBorrower);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                if (v == buttonCheck) {
                    final String findBorrower = editTextFindFriend.getText().toString();
                    myRef.orderByChild("Borrower").equalTo(findBorrower).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            String findKey = dataSnapshot.getKey();
                            Book findBook = dataSnapshot.getValue(Book.class);

                            textViewShowBooks.setText("The borrowered book is: " + findBook.title);
                            //String findTitle = myRef.child(findKey).child("title")
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        }
    }
}